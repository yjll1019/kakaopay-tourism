package com.kakaopay.tourism.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Region {
    @Id
    @GenericGenerator(name = "region_id",
            strategy = "com.kakaopay.tourism.domain.generator.RegionIdGenerator")
    @GeneratedValue(generator = "region_id")
    private String id;

    private String rootRegion;

    private String subRegion;

    public Region() {
    }

    public Region(String rootRegion, String subRegion) {
        this.rootRegion = rootRegion;
        this.subRegion = subRegion;
    }
}

