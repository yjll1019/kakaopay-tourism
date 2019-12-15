package com.kakaopay.tourism.service.dto.request;

public class ProgramRecommendRequestDto {
    private String regionName;

    private String keyword;

    public ProgramRecommendRequestDto() {
    }

    public ProgramRecommendRequestDto(String regionName, String keyword) {
        this.regionName = regionName;
        this.keyword = keyword;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getKeyword() {
        return keyword;
    }
}
