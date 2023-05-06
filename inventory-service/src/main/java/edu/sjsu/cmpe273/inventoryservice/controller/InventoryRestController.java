package edu.sjsu.cmpe273.inventoryservice.controller;

import edu.sjsu.cmpe273.inventoryservice.model.Inventory;
import edu.sjsu.cmpe273.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryRestController {

    private final InventoryRepository inventoryRepository;

    @GetMapping(value = "/{skuCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean isInStock(@PathVariable String skuCode) {
        log.info("Checking SkuCode {} is in Stock.", skuCode);
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Cannot Find Product by Sku Code" + skuCode));
        return inventory.getStock() > 0;
    }

}
