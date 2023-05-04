package edu.sjsu.cmpe273.orderservice.dto;

import edu.sjsu.cmpe273.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderDto {
    private List<OrderLineItems> orderLineItemsList;
}
