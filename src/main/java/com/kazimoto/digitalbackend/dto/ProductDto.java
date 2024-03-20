package com.kazimoto.digitalbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    private String rowId;

    private String productName;

    private String description;

    private Integer status = 1;
}
