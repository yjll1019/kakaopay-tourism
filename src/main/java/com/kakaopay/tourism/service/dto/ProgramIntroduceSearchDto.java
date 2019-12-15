package com.kakaopay.tourism.service.dto;

public class ProgramIntroduceSearchDto {
    private String region;

    private int count;

    public ProgramIntroduceSearchDto() {
    }

    public ProgramIntroduceSearchDto(String region, int count) {
        this.region = region;
        this.count = count;
    }

    public String getRegion() {
        return region;
    }

    public int getCount() {
        return count;
    }
}
