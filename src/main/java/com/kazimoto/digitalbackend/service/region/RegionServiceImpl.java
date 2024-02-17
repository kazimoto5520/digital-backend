package com.kazimoto.digitalbackend.service.region;

import com.kazimoto.digitalbackend.dto.RegionDto;
import com.kazimoto.digitalbackend.entity.District;
import com.kazimoto.digitalbackend.entity.Region;
import com.kazimoto.digitalbackend.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {
    public final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region saveRegion(RegionDto region) {
        if (regionRepository.existsByName(region.getRegionName())) {
            throw new RuntimeException(region.getRegionName() + " already exists");
        }

        Region newRegion = new Region();
        newRegion.setName(region.getRegionName());
        newRegion.setId(region.getRegionId());
        log.info("Region Name: {}", newRegion.getName());
        log.info("Region ID: {}", newRegion.getId());
        return regionRepository.save(newRegion);
    }

    @Override
    public Region getSingleRegion(Long id) {
        log.info("Getting single region with id {}", id);
        return regionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No region with id " + id));
    }

    @Override
    public Region updateRegion(Long id, RegionDto region) {
        Region existedRegion = regionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Region does not exist"));

        log.info("Region found: {}", existedRegion.getName());

        existedRegion.setName(region.getRegionName());
        existedRegion.setStatus(Integer.valueOf(region.getStatus()));
        log.info("Name update {}", existedRegion.getName());
        return regionRepository.save(existedRegion);
    }

    @Override
    public void deleteRegion(Long id) {
        Region regionToDeleted =
                regionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Region does not exist"));

        regionRepository.delete(regionToDeleted);
    }

}
