package com.leemore.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsListDto {

    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
