package edu.sjsu.cmpe273.orderservice.controller;

import edu.sjsu.cmpe273.orderservice.client.InventoryClient;
import edu.sjsu.cmpe273.orderservice.dto.OrderDto;
import edu.sjsu.cmpe273.orderservice.model.Order;
import edu.sjsu.cmpe273.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final StreamBridge streamBridge;
    //private final ExecutorService traceableExecutorService;

    @GetMapping
    public String get() {
        return "get order";
    }

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto) {
        //circuitBreakerFactory.configureExecutorService(traceableExecutorService);
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory");
        Supplier<Boolean> booleanSupplier = () -> orderDto.getOrderLineItemsList().stream()
                .allMatch(lineItem -> {
                    log.info("Making call to Inventory Service for skuCode {}", lineItem.getSkuCode());
                    return inventoryClient.checkStock(lineItem.getSkuCode());
                });
        boolean allProductsInStock = circuitBreaker.run(booleanSupplier, throwable -> handleErrorCase());
        if(allProductsInStock) {
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);

            log.info("Sending Order Details with Order Id {} to Notification Service.", order.getId());
            streamBridge.send("notificationEventSupplier-out-0", new GenericMessage<>(order.getId()));
            return "Order Place Successfully";
        }
        return "Order Failed, One of the products in the order is not in stock";
    }

    private Boolean handleErrorCase() {
        return false;
    }

}
