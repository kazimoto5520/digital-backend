package com.kazimoto.digitalbackend.service.district;

import com.kazimoto.digitalbackend.dto.DistrictDto;
import com.kazimoto.digitalbackend.dto.DistrictResponseDto;
import com.kazimoto.digitalbackend.dto.RegionDto;
import com.kazimoto.digitalbackend.entity.District;
import com.kazimoto.digitalbackend.entity.Region;
import com.kazimoto.digitalbackend.repository.DistrictRepository;
import com.kazimoto.digitalbackend.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository, RegionRepository regionRepository) {
        this.districtRepository = districtRepository;

        this.regionRepository = regionRepository;
    }


    @Override
    public List<DistrictResponseDto> getAllDistrict() {
        List<District> districts = districtRepository.findAll();

        return districts.stream()
                .map(district -> {
                    DistrictResponseDto responseDto = new DistrictResponseDto();
                    responseDto.setId(district.getId());
                    responseDto.setDistrictName(district.getName());
                    responseDto.setRegionName(district.getRegion().getName());
                    responseDto.setStatus(district.getStatus());
                    responseDto.setCreatedAt(district.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_TIME));
                    responseDto.setUpdatedAt(district
                            .getUpdatedAt() != null ? district.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_TIME) : null);
                    return responseDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DistrictResponseDto saveDistrict(DistrictDto dto) {
        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new NoSuchElementException("Region not found with ID: " + dto.getRegionId()));

        District newDistrict = new District();

        newDistrict.setName(dto.getDistrictName());
        newDistrict.setRegion(region);

        log.info("District Name: {}", newDistrict.getName());
        log.info("Region Name: {}", newDistrict.getRegion());

        if (districtRepository.existsByNameAndRegion(dto.getDistrictName(), region)){
            throw new RuntimeException(dto.getDistrictName() + " already exists in " + region.getName());
        }
        District savedDistrict = districtRepository.save(newDistrict);

        DistrictResponseDto district = new DistrictResponseDto();
        district.setId(savedDistrict.getId());
        district.setDistrictName(savedDistrict.getName());
        district.setRegionName(savedDistrict.getRegion().getName());
        district.setStatus(savedDistrict.getStatus());
        district.setCreatedAt(savedDistrict.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE));
        district.setUpdatedAt(savedDistrict
                .getUpdatedAt() != null ? savedDistrict.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE) : null);

        return district;
    }

    @Override
    public DistrictResponseDto getSingleDistrict(Long id) {
        District district = districtRepository
                .findById(id).orElseThrow(() -> new NoSuchElementException("District not found"));

        DistrictResponseDto responseDto = new DistrictResponseDto();
        responseDto.setId(district.getId());
        responseDto.setDistrictName(district.getName());
        responseDto.setRegionName(district.getRegion().getName());
        responseDto.setStatus(district.getStatus());
        responseDto.setCreatedAt(district.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE));
        responseDto.setUpdatedAt(district
                .getUpdatedAt() != null ? district.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE) : null);
        return responseDto;
    }

    @Override
    public District updateDistrict(Long id, DistrictDto dto) {
        return null;
    }

    @Override
    public void deleteDistrict(Long id) {

    }
}
