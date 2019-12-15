package com.kakaopay.tourism.web.controller;

import com.kakaopay.tourism.service.ProgramService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    private ProgramService programService;

    public FileUploadController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/files")
    public ResponseEntity registerWithFile(@RequestParam MultipartFile file) {
        programService.save(file);
        return ResponseEntity.ok().build();
    }
}
