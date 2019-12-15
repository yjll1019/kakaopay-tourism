package com.kakaopay.tourism.service.dto.response;

import java.util.stream.Collectors;

import com.kakaopay.tourism.domain.Program;
import com.kakaopay.tourism.domain.Theme;

public class ProgramSimpleInfoDto {
    private String prgmName;

    private String theme;

    public ProgramSimpleInfoDto() {
    }

    public ProgramSimpleInfoDto(String prgmName, String theme) {
        this.prgmName = prgmName;
        this.theme = theme;
    }

    public String getPrgmName() {
        return prgmName;
    }

    public String getTheme() {
        return theme;
    }

    public static ProgramSimpleInfoDto toDto(Program program) {
        return new ProgramSimpleInfoDto(program.getName(), program.getThemes().stream()
                .map(Theme::getName).collect(Collectors.joining(" ")));
    }
}
