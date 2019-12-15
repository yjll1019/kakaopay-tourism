package com.kakaopay.tourism.web.controller;

import java.util.List;

import com.kakaopay.tourism.service.ProgramService;
import com.kakaopay.tourism.service.dto.request.ProgramCreateRequestDto;
import com.kakaopay.tourism.service.dto.response.ProgramResponseDto;
import com.kakaopay.tourism.service.dto.response.ProgramSearchResponseDtoWithContents;
import com.kakaopay.tourism.service.dto.response.ProgramSearchResponseDtoWithIntroduce;
import com.kakaopay.tourism.service.dto.response.ProgramSearchResponseDtoWithRegionName;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProgramController {
    private ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/programs")
    public ResponseEntity register(@RequestBody ProgramCreateRequestDto programCreateRequestDto) {
        programService.save(programCreateRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/regions/{regionId}")
    public ResponseEntity<List<ProgramResponseDto>> findByRegionId(@PathVariable String regionId) {
        return ResponseEntity.ok(programService.findByRegionId(regionId));
    }

    @PutMapping("/programs/{programId}")
    public ResponseEntity update(@PathVariable Long programId, @RequestBody ProgramCreateRequestDto programCreateRequestDto) {
        programService.update(programId, programCreateRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/programs")
    public ResponseEntity<List<ProgramSearchResponseDtoWithRegionName>> findByRegionName(@RequestParam String regionName) {
        return ResponseEntity.ok(programService.findByRegionKeyword(regionName));
    }

    @GetMapping("/programs/search/introduces")
    public ResponseEntity<ProgramSearchResponseDtoWithIntroduce> findByProgramIntroduce(@RequestParam String introduceKeyword) {
        return ResponseEntity.ok(programService.findByProgramIntroduce(introduceKeyword));
    }

    @GetMapping("/programs/search/contents")
    public ResponseEntity<ProgramSearchResponseDtoWithContents> findByProgramContents(@RequestParam String contentsKeyword) {
        return ResponseEntity.ok(programService.findByProgramContents(contentsKeyword));
    }
}
