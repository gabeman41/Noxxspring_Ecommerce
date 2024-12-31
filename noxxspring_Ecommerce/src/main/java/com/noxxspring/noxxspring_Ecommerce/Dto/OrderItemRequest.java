package com.noxxspring.noxxspring_Ecommerce.Dto;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Long productId;
    private int quantity;

}
