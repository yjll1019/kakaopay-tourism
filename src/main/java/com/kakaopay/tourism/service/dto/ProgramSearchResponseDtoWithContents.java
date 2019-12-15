package com.kakaopay.tourism.service.dto;

public class ProgramSearchResponseDtoWithContents {
    private String keyword;

    private int count;

    public ProgramSearchResponseDtoWithContents() {
    }

    public ProgramSearchResponseDtoWithContents(String keyword, int count) {
        this.keyword = keyword;
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getCount() {
        return count;
    }
}
