package com.kakaopay.tourism.web.controller;

import java.io.File;

import com.kakaopay.tourism.service.dto.request.ProgramRecommendRequestDto;
import com.kakaopay.tourism.service.dto.request.ProgramCreateRequestDto;
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
        ProgramCreateRequestDto programCreateRequestDto = new ProgramCreateRequestDto("강원도 체험", "강원도 여행", "강원도로 떠나는 여행",
                "생태체험", "강원도 양양");

        webTestClient.post()
                .uri("/programs")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(programCreateRequestDto), ProgramCreateRequestDto.class)
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
        ProgramCreateRequestDto programCreateRequestDto = new ProgramCreateRequestDto("강원도 체험", "강원도 여행", "강원도로 떠나는 여행",
                "생태체험", "강원도 양양");

        webTestClient.put()
                .uri("/programs/1")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(programCreateRequestDto), ProgramCreateRequestDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void search_region_name() {
        webTestClient.get()
                .uri("/programs?regionName=남도")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void search_introduce() {
        webTestClient.get()
                .uri("/programs/search/introduces?introduceKeyword=세계문화유산")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void search_contents() {
        webTestClient.get()
                .uri("/programs/search/contents?contentsKeyword=문화")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void recommend_program() {
        ProgramRecommendRequestDto recommendRequestDto
                = new ProgramRecommendRequestDto("속초", "생태");

        webTestClient.post()
                .uri("/programs/recommend")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recommendRequestDto), ProgramRecommendRequestDto.class)
                .exchange()
                .expectStatus().isOk();
    }
}