package com.kakaopay.tourism.service;

import com.kakaopay.tourism.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class RegionServiceTest {
    @InjectMocks
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Test
    void save_region() {
        String rootRegion = "강원도";
        String subRegion = "속초";

        regionService.save(rootRegion, subRegion);

        verify(regionRepository, Mockito.times(1))
                .findByRootRegionAndRegion(rootRegion, subRegion);
        verify(regionRepository, Mockito.times(1)).save(any());
    }
}