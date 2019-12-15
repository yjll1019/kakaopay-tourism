package com.kakaopay.tourism.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.kakaopay.tourism.domain.Program;

public class ProgramSearchResponseDtoWithRegionName {
    private String regionName;

    private List<ProgramSimpleInfoDto> programSimpleInfoDtos;

    public ProgramSearchResponseDtoWithRegionName() {
    }

    public ProgramSearchResponseDtoWithRegionName(String regionName, List<ProgramSimpleInfoDto> programSimpleInfoDtos) {
        this.regionName = regionName;
        this.programSimpleInfoDtos = programSimpleInfoDtos;
    }

    public String getRegionName() {
        return regionName;
    }

    public List<ProgramSimpleInfoDto> getProgramSimpleInfoDtos() {
        return programSimpleInfoDtos;
    }

    public static ProgramSearchResponseDtoWithRegionName toDto(String name, List<Program> programs) {
//        final String s = programs.getThemes().stream()
//                .map(Theme::getName).collect(Collectors.joining(" "));

        return new ProgramSearchResponseDtoWithRegionName(name,
                programs.stream().map(ProgramSimpleInfoDto::toDto)
                        .collect(Collectors.toList()));
//        return new ProgramSearchResponseDtoWithRegionName(name, ProgramSimpleInfoDto.toDto(programs));
    }
}
