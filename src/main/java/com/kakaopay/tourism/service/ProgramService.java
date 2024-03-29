package com.kakaopay.tourism.service;

import java.util.*;
import java.util.stream.Collectors;

import com.kakaopay.tourism.domain.Program;
import com.kakaopay.tourism.domain.Region;
import com.kakaopay.tourism.domain.Theme;
import com.kakaopay.tourism.repository.ProgramRepository;
import com.kakaopay.tourism.service.dto.request.ProgramCreateRequestDto;
import com.kakaopay.tourism.service.dto.request.ProgramRecommendRequestDto;
import com.kakaopay.tourism.service.dto.response.*;
import com.kakaopay.tourism.service.exception.IdNotFoundException;
import com.kakaopay.tourism.service.recommender.strategy.WeightStrategyForTheme;
import com.kakaopay.tourism.service.recommender.ProgramRecommender;
import com.kakaopay.tourism.util.DataFileReader;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProgramService {
    private static final int PROGRAM_NAME_COLUMN = 1;
    private static final int THEME_COLUMN = 2;
    private static final int REGION_COLUMN = 3;
    private static final int PROGRAM_INTRODUCE_COLUMN = 4;
    private static final int PROGRAM_NAME_CONTENTS = 5;

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

    public void save(ProgramCreateRequestDto programCreateRequestDto) {
        List<Theme> themes = themeService.saveThemes(programCreateRequestDto.getTheme());
        List<Region> regions = regionService.saveRegions(programCreateRequestDto.getRegion());

        Program program = programCreateRequestDto.toEntity();
        program.addThemes(themes);
        program.addRegions(regions);

        programRepository.save(program);
    }

    public List<ProgramResponseDto> findByRegionId(String regionId) {
        List<Program> programs = programRepository.findByRegions_Id(regionId);

        return programs.stream().map(ProgramResponseDto::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(Long id, ProgramCreateRequestDto programCreateRequestDto) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id + "가 존재하지 않습니다."));

        List<Theme> themes = themeService.saveThemes(programCreateRequestDto.getTheme());
        List<Region> regions = regionService.saveRegions(programCreateRequestDto.getRegion());

        program.updateThemes(themes);
        program.updateRegions(regions);
        program.updateName(programCreateRequestDto.getProgramName());
        program.updateIntroduce(programCreateRequestDto.getProgramIntroduce());
        program.updateContents(programCreateRequestDto.getProgramContents());

        programRepository.save(program);
    }

    public List<ProgramSearchResponseDtoWithRegionName> findByRegionKeyword(String regionName) {
        List<Region> regions = regionService.findByNameContaining(regionName);

        return regions.stream().map(region -> ProgramSearchResponseDtoWithRegionName.toDto(region.getId(),
                programRepository.findByRegions_Id(region.getId())))
                .collect(Collectors.toList());
    }

    public ProgramSearchResponseDtoWithIntroduce findByProgramIntroduce(String introduceKeyword) {
        List<Program> programs = programRepository.findByIntroduceContaining(introduceKeyword);

        Map<String, Integer> result = new HashMap<>();

        for (Program program : programs) {
            for (Region region : program.getRegions()) {
                String regionName = region.getName();
                if (Objects.isNull(result.get(regionName))) {
                    result.put(regionName, 1);
                    continue;
                }
                result.put(regionName, result.get(regionName) + 1);
            }
        }

        return new ProgramSearchResponseDtoWithIntroduce(introduceKeyword,
                result.entrySet().stream().map(region -> new ProgramIntroduceSearchResponseDto(region.getKey(), region.getValue()))
                        .collect(Collectors.toList()));
    }

    public ProgramSearchResponseDtoWithContents findByProgramContents(String contentsKeyword) {
        List<Program> programs = programRepository.findByContentsContaining(contentsKeyword);

        return new ProgramSearchResponseDtoWithContents(contentsKeyword,
                programs.stream()
                        .mapToInt(program -> program.countOfKeyword(contentsKeyword))
                        .sum());
    }

    public ProgramRecommendResponseDto recommendProgram(ProgramRecommendRequestDto programRecommendDto) {
        List<Region> regions = regionService.findByNameContaining(programRecommendDto.getRegionName());
        List<Program> programs = new ArrayList<>();

        for (Region region : regions) {
            List<Program> program = (programRepository.findByRegions_Id(region.getId()));
            programs.addAll(program);
        }

        return new ProgramRecommendResponseDto(ProgramRecommender.recommend(
                programRecommendDto.getKeyword(), programs, new WeightStrategyForTheme()).getId());
    }
}
