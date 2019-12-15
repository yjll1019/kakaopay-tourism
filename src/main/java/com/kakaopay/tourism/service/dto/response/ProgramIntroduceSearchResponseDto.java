package com.kakaopay.tourism.service.dto.response;

public class ProgramIntroduceSearchResponseDto {
    private String region;

    private int count;

    public ProgramIntroduceSearchResponseDto() {
    }

    public ProgramIntroduceSearchResponseDto(String region, int count) {
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
