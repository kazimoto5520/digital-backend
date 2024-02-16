package com.kazimoto.digitalbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DistrictResponseDto {
    private String districtName;
    private String regionName;
    private Integer status;
    private String createdAt;
    private String updatedAt;
}
