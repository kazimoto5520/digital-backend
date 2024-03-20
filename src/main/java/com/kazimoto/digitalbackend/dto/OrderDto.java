package com.kazimoto.digitalbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDto {
    private String rowId;
    private String orderName;
    private String description;
    private Double amount;
    private Integer quantity;
    private String lockNumber;
    private String shippingAddress;
    private String userId;
    private String productId;
    private Integer status = 1;
}
