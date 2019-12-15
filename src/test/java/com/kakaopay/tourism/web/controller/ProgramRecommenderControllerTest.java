package com.kakaopay.tourism.web.controller;

import com.kakaopay.tourism.service.dto.request.ProgramRecommendRequestDto;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ProgramRecommenderControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void recommend_program() {
        ProgramRecommendRequestDto recommendRequestDto
                = new ProgramRecommendRequestDto("양양", "문화");

        webTestClient.post()
                .uri("/programs/recommend")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recommendRequestDto), ProgramRecommendRequestDto.class)
                .exchange()
                .expectStatus().isOk();
    }
}