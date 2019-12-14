package com.kakaopay.tourism.service;

import com.kakaopay.tourism.domain.Region;
import com.kakaopay.tourism.repository.RegionRepository;

import org.springframework.stereotype.Service;

@Service
public class RegionService {
    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region save(String rootRegion, String region) {
        return regionRepository.findByRootRegionAndRegion(rootRegion, region)
            .orElseGet(() -> regionRepository.save(new Region(rootRegion, region)));
    }
}
