package com.kakaopay.tourism.web.controller;

import java.util.List;

import com.kakaopay.tourism.service.ProgramService;
import com.kakaopay.tourism.service.dto.ProgramResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProgramController {
    private ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/files")
    public ResponseEntity registerWithFile(@RequestParam MultipartFile file) {
        programService.save(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/regions/{regionId}")
    public ResponseEntity<List<ProgramResponseDto>> findByRegionId(@PathVariable String regionId) {
        return ResponseEntity.ok(programService.findByRegionId(regionId));
    }
}
