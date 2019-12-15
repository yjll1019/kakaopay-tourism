package com.kakaopay.tourism.service.dto.response;

import java.util.List;

import com.kakaopay.tourism.service.dto.response.ProgramIntroduceSearchResponseDto;

public class ProgramSearchResponseDtoWithIntroduce {
    private String keyword;

    private List<ProgramIntroduceSearchResponseDto> programIntroduceSearchResponseDtoList;

    public ProgramSearchResponseDtoWithIntroduce() {
    }

    public ProgramSearchResponseDtoWithIntroduce(String keyword, List<ProgramIntroduceSearchResponseDto> programIntroduceSearchResponseDtoList) {
        this.keyword = keyword;
        this.programIntroduceSearchResponseDtoList = programIntroduceSearchResponseDtoList;
    }

    public String getKeyword() {
        return keyword;
    }

    public List<ProgramIntroduceSearchResponseDto> getProgramIntroduceSearchResponseDtoList() {
        return programIntroduceSearchResponseDtoList;
    }
}
