package com.kakaopay.tourism.web.controller;

import java.io.File;

import com.kakaopay.tourism.service.dto.request.ProgramRequestDto;
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
public class ProgramControllerTest {
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

    @Test
    void register_program() {
        ProgramRequestDto programRequestDto = new ProgramRequestDto("강원도 체험", "강원도 여행", "강원도로 떠나는 여행",
                "생태체험", "강원도 양양");

        webTestClient.post()
                .uri("/programs")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(programRequestDto), ProgramRequestDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void find_by_region_id() {
        webTestClient.get()
                .uri("/regions/region02e22707")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void update_program() {
        ProgramRequestDto programRequestDto = new ProgramRequestDto("강원도 체험", "강원도 여행", "강원도로 떠나는 여행",
                "생태체험", "강원도 양양");

        webTestClient.put()
                .uri("/programs/1")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(programRequestDto), ProgramRequestDto.class)
                .exchange()
                .expectStatus().isOk();
    }
}