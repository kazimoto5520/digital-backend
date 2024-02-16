package com.kazimoto.digitalbackend.service.region;

import com.kazimoto.digitalbackend.dto.RegionDto;
import com.kazimoto.digitalbackend.entity.Region;

import java.util.List;

public interface RegionService {
    List<Region> getAllRegions();

    Region saveRegion(RegionDto region);

    Region getSingleRegion(Long id);

    Region updateRegion(Long id, RegionDto region);

    void deleteRegion(Long id);
}
