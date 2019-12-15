package com.kakaopay.tourism.service;

import java.util.List;
import java.util.stream.Collectors;

import com.kakaopay.tourism.domain.Region;
import com.kakaopay.tourism.repository.RegionRepository;
import com.kakaopay.tourism.service.parser.RegionParser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionService {
    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region save(String region) {
        return regionRepository.findByName(region)
                .orElseGet(() -> regionRepository.save(new Region(region)));
    }

    @Transactional
    public List<Region> saveRegions(String regions) {
        List<String> subRegions = RegionParser.parse(regions);
        return subRegions.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    public List<Region> findByNameContaining(String regionName) {
        return regionRepository.findByNameContaining(regionName);
    }
}
