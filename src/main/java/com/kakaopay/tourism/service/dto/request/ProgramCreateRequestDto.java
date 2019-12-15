package com.kakaopay.tourism.service.dto.request;

import java.util.ArrayList;

import com.kakaopay.tourism.domain.Program;

public class ProgramCreateRequestDto {
    private String programName;

    private String programContents;

    private String programIntroduce;

    private String theme;

    private String region;

    public ProgramCreateRequestDto() {
    }

    public ProgramCreateRequestDto(String programName, String programContents, String programIntroduce, String theme, String region) {
        this.programName = programName;
        this.programContents = programContents;
        this.programIntroduce = programIntroduce;
        this.theme = theme;
        this.region = region;
    }

    public String getProgramName() {
        return programName;
    }

    public String getProgramContents() {
        return programContents;
    }

    public String getProgramIntroduce() {
        return programIntroduce;
    }

    public String getTheme() {
        return theme;
    }

    public String getRegion() {
        return region;
    }

    public Program toEntity() {
        return new Program(programName, programIntroduce, programContents,
                new ArrayList<>(), new ArrayList<>());
    }
}
