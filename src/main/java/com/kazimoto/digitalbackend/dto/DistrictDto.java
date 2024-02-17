package com.kazimoto.digitalbackend.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DistrictDto {
    private String districtName;
    @NonNull
    private Long regionId;
    private Integer status = 1;
}
