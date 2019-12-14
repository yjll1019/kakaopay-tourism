package com.kakaopay.tourism.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.kakaopay.tourism.repository.ProgramRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class ProgramServiceTest {
    @InjectMocks
    private ProgramService programService;

    @Mock
    private ProgramRepository programRepository;

    @Mock
    private RegionService regionService;

    @Mock
    private ThemeService themeService;

    private String fileDirectory = "./src/test/resources/";

    @Test
    void save_program() throws IOException {
        String filePath = String.format("%s%s", fileDirectory, "csvTestFile.csv");
        File csvFile = new File(filePath);

        MultipartFile multipartFile = new MockMultipartFile("csvTestFile.csv",
                new FileInputStream(csvFile));

        programService.save(multipartFile);

        verify(themeService, Mockito.atLeastOnce()).saveThemes(anyString());
        verify(regionService, Mockito.atLeastOnce()).saveRegions(anyString());
        verify(programRepository, Mockito.atLeastOnce()).save(any());
    }
}