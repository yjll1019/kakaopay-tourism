package com.kakaopay.tourism.web.controller;

import java.io.File;

import com.kakaopay.tourism.service.dto.request.ProgramCreateRequestDto;
import com.kakaopay.tourism.service.dto.request.ProgramRecommendRequestDto;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class FileUploadControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void register_file() {
        String fileDirectory = "./src/test/resources/";
        String filePath = String.format("%s%s", fileDirectory, "csvTestFile.csv");
        File csvFile = new File(filePath);

        webTestClient.post()
                .uri("/files")
                .contentType(MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", new FileSystemResource(csvFile)))
                .exchange()
                .expectStatus().isOk();
    }
}