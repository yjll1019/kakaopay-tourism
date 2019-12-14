package com.kakaopay.tourism.service;

import java.io.File;
import java.util.List;

import com.kakaopay.tourism.domain.Program;
import com.kakaopay.tourism.domain.Region;
import com.kakaopay.tourism.domain.Theme;
import com.kakaopay.tourism.repository.ProgramRepository;
import com.kakaopay.tourism.util.DataFileReader;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProgramService {
    public static final int PROGRAM_NAME_COLUMN = 1;
    public static final int THEME_COLUMN = 2;
    public static final int REGION_COLUMN = 3;
    public static final int PROGRAM_INTRODUCE_COLUMN = 4;
    public static final int PROGRAM_NAME_CONTENTS = 5;

    private ProgramRepository programRepository;

    private ThemeService themeService;

    private RegionService regionService;

    public ProgramService(ProgramRepository programRepository, ThemeService themeService, RegionService regionService) {
        this.programRepository = programRepository;
        this.themeService = themeService;
        this.regionService = regionService;
    }

    public void save(MultipartFile csvFile) {
        List<String[]> data = DataFileReader.readFile(csvFile);

        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);

            List<Theme> themes = themeService.saveThemes(row[THEME_COLUMN]);

            List<Region> regions = regionService.saveRegions(row[REGION_COLUMN]);

            Program program = new Program(row[PROGRAM_NAME_COLUMN], row[PROGRAM_INTRODUCE_COLUMN],
                    row[PROGRAM_NAME_CONTENTS], regions, themes);

            programRepository.save(program);
        }
    }
}
