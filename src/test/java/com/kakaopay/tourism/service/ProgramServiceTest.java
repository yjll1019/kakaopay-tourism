package com.kakaopay.tourism.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import com.kakaopay.tourism.domain.Program;
import com.kakaopay.tourism.domain.Region;
import com.kakaopay.tourism.repository.ProgramRepository;
import com.kakaopay.tourism.service.dto.request.ProgramCreateRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
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
    void save_program_with_file() throws IOException {
        String filePath = String.format("%s%s", fileDirectory, "csvTestFile.csv");
        File csvFile = new File(filePath);

        MultipartFile multipartFile = new MockMultipartFile("csvTestFile.csv",
                new FileInputStream(csvFile));

        programService.save(multipartFile);

        verify(themeService, Mockito.atLeastOnce()).saveThemes(anyString());
        verify(regionService, Mockito.atLeastOnce()).saveRegions(anyString());
        verify(programRepository, Mockito.atLeastOnce()).save(any());
    }

    @Test
    void find_by_region_id() {
        Program program = new Program("강원도", "강원도 여행", "강원도로 떠나는 여행",
                Collections.emptyList(), Collections.emptyList());
        ReflectionTestUtils.setField(program, "id", 1L);

        String regionId = "region02e22707";
        given(programRepository.findByRegions_Id(regionId)).willReturn(Arrays.asList(program));

        programService.findByRegionId(regionId);

        verify(programRepository, Mockito.atLeastOnce()).findByRegions_Id(regionId);
    }

    @Test
    void save_program() {
        ProgramCreateRequestDto requestDto = new ProgramCreateRequestDto("강원도 체험", "강원도 여행", "강원도로 떠나는 여행",
                "생태체험", "강원도 양양");
        programService.save(requestDto);

        verify(themeService, Mockito.atLeastOnce()).saveThemes(anyString());
        verify(regionService, Mockito.atLeastOnce()).saveRegions(anyString());
        verify(programRepository, Mockito.atLeastOnce()).save(any());
    }

    @Test
    void update_program() {
        ProgramCreateRequestDto requestDto = new ProgramCreateRequestDto("강원도 체험", "강원도 여행",
                "강원도로 떠나는 여행", "생태체험", "강원도 양양");
        Program program = new Program("강원도", "강원도 여행", "강원도로 떠나는 여행",
                Collections.emptyList(), Collections.emptyList());
        ReflectionTestUtils.setField(program, "id", 1L);

        given(programRepository.findById(1L)).willReturn(Optional.of(program));

        programService.update(1L, requestDto);

        verify(themeService, Mockito.atLeastOnce()).saveThemes(anyString());
        verify(regionService, Mockito.atLeastOnce()).saveRegions(anyString());
        verify(programRepository, Mockito.atLeastOnce()).save(any());
    }

    @Test
    void search_region_keyword() {
        String id = "region1235";
        String regionName = "regionName";

        Region region = new Region(regionName);
        ReflectionTestUtils.setField(region, "id", id);

        given(regionService.findByNameContaining(regionName)).willReturn(Arrays.asList(region));
        programService.findByRegionKeyword(regionName);

        verify(programRepository, Mockito.atLeastOnce()).findByRegions_Id(id);
    }

    @Test
    void search_introduce_keyword() {
        Program program = new Program("강원도", "강원도 여행", "강원도로 떠나는 여행",
                Collections.emptyList(), Collections.emptyList());
        ReflectionTestUtils.setField(program, "id", 1L);

        given(programRepository.findById(1L)).willReturn(Optional.of(program));

        String introduceKeyword = "떠나는";

        programService.findByProgramIntroduce(introduceKeyword);

        verify(programRepository, Mockito.atLeastOnce()).findByIntroduceContaining(introduceKeyword);
    }

    @Test
    void search_contents_keyword() {
        Program program = new Program("강원도", "강원도 여행", "여행을 가요. 강원도로 떠나는 여행",
                Collections.emptyList(), Collections.emptyList());
        ReflectionTestUtils.setField(program, "id", 1L);

        given(programRepository.findById(1L)).willReturn(Optional.of(program));

        String contentsKeyword = "여행";

        programService.findByProgramContents(contentsKeyword);

        verify(programRepository, Mockito.atLeastOnce()).findByContentsContaining(contentsKeyword);
    }
}