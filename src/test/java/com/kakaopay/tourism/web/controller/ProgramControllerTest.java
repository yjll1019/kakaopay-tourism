package com.kakaopay.tourism.web.controller;

import com.kakaopay.tourism.service.dto.request.ProgramCreateRequestDto;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ProgramControllerTest {
    @Autowired
    private WebTestClient webTestClient;

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
}