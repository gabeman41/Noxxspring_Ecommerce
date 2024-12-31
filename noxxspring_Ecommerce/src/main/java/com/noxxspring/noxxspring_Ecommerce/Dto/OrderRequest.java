package com.noxxspring.noxxspring_Ecommerce.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    private BigDecimal price;
    private List<OrderItemRequest> items;
}
