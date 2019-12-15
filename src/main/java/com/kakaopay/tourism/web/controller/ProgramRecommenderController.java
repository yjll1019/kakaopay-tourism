package com.kakaopay.tourism.web.controller;

import com.kakaopay.tourism.service.ProgramService;
import com.kakaopay.tourism.service.dto.request.ProgramRecommendRequestDto;
import com.kakaopay.tourism.service.dto.response.ProgramRecommendResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgramRecommenderController {
    private ProgramService programService;

    public ProgramRecommenderController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/programs/recommend")
    public ResponseEntity<ProgramRecommendResponseDto> recommendProgram(@RequestBody ProgramRecommendRequestDto programRecommendRequestDto) {
        return ResponseEntity.ok(programService.recommendProgram(programRecommendRequestDto));
    }
}
