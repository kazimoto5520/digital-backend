package com.kazimoto.digitalbackend.service.region;

import com.kazimoto.digitalbackend.entity.Region;

import java.util.List;

public interface RegionService {
    List<Region> getAllRegions();

    Region saveRegion(Region region);

    Region getSingleRegion(Long id);

    Region updateRegion(Long id, Region region);
}
