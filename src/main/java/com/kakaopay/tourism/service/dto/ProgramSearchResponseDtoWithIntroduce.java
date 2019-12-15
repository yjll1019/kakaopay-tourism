package com.kakaopay.tourism.service.dto;

import java.util.List;

public class ProgramSearchResponseDtoWithIntroduce {
    private String keyword;

    private List<ProgramIntroduceSearchDto> programIntroduceSearchDtoList;

    public ProgramSearchResponseDtoWithIntroduce() {
    }

    public ProgramSearchResponseDtoWithIntroduce(String keyword, List<ProgramIntroduceSearchDto> programIntroduceSearchDtoList) {
        this.keyword = keyword;
        this.programIntroduceSearchDtoList = programIntroduceSearchDtoList;
    }

    public String getKeyword() {
        return keyword;
    }

    public List<ProgramIntroduceSearchDto> getProgramIntroduceSearchDtoList() {
        return programIntroduceSearchDtoList;
    }
}
