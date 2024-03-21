package com.kazimoto.digitalbackend.dto.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String rowId;
    private String orderName;
    private String description;
    private Double amount;
    private Integer quantity;
    private String lockNumber;
    private String shippingAddress;
    private String user;
    private String product;
    private Integer status;
}
