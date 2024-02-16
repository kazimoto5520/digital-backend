package com.kazimoto.digitalbackend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegionDto {
    private Long regionId;
    @NotEmpty(message = "Region name should not be empty")
    private String regionName;
    private String status;
}
