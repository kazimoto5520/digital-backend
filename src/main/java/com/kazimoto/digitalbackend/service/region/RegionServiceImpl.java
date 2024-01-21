package com.kazimoto.digitalbackend.service.region;

import com.kazimoto.digitalbackend.entity.Region;
import com.kazimoto.digitalbackend.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService{
    public final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region saveRegion(Region region) {
        Region savedRegion = new Region();
        savedRegion.setName(region.getName());
        return regionRepository.save(savedRegion);
    }

    @Override
    public Region getSingleRegion(Long id) {
        return regionRepository.findById(id).orElseThrow();
    }

    @Override
    public Region updateRegion(Long id, Region region) {
        Region existedRegion = regionRepository.findById(id).orElse(null);

        if (existedRegion != null){
            existedRegion.setName(region.getName());
            return regionRepository.save(existedRegion);
        }else {
            return null;
        }
    }

}
