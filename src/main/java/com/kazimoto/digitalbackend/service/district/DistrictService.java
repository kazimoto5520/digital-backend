package com.kazimoto.digitalbackend.service.district;

import com.kazimoto.digitalbackend.dto.DistrictDto;
import com.kazimoto.digitalbackend.dto.DistrictResponseDto;
import com.kazimoto.digitalbackend.dto.RegionDto;
import com.kazimoto.digitalbackend.entity.District;
import com.kazimoto.digitalbackend.entity.Region;

import java.util.List;

public interface DistrictService {

    List<DistrictResponseDto> getAllDistrict();

    DistrictResponseDto saveDistrict(DistrictDto dto);

    DistrictResponseDto getSingleDistrict(Long id);

    DistrictResponseDto updateDistrict(Long id, DistrictDto dto);

    void deleteDistrict(Long id);
}
