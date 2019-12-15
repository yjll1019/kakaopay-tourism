package com.kakaopay.tourism.service.dto;

public class ProgramRecommendResponseDto {
    private Long programCode;

    public ProgramRecommendResponseDto() {
    }

    public ProgramRecommendResponseDto(Long programCode) {
        this.programCode = programCode;
    }

    public Long getProgram() {
        return programCode;
    }
}
