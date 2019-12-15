package com.kakaopay.tourism.service.dto.response;

import java.util.stream.Collectors;

import com.kakaopay.tourism.domain.Program;
import com.kakaopay.tourism.domain.Region;
import com.kakaopay.tourism.domain.Theme;

public class ProgramResponseDto {
    private String name;

    private String introduce;

    private String contents;

    private String regions;

    private String themes;

    public ProgramResponseDto() {
    }

    public ProgramResponseDto(String name, String introduce, String contents, String regions, String themes) {
        this.name = name;
        this.introduce = introduce;
        this.contents = contents;
        this.regions = regions;
        this.themes = themes;
    }

    public static ProgramResponseDto toResponseDto(Program program) {
        return new ProgramResponseDto(program.getName(), program.getIntroduce(), program.getContents(),
                program.getRegions().stream()
                        .map(Region::getName).collect(Collectors.joining(" ")),
                program.getThemes().stream()
                        .map(Theme::getName).collect(Collectors.joining(" ")));
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getContents() {
        return contents;
    }

    public String getRegions() {
        return regions;
    }

    public String getThemes() {
        return themes;
    }
}
