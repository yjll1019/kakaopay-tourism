package com.kakaopay.tourism.service.parser;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegionParserTest {
    @Test
    void parse_data_with_remove_keyword() {
        List<String> actual = RegionParser.parse("신안군 도초면 우이도 일원");
        assertThat(Arrays.asList("신안군 도초면 우이도")).isEqualTo(actual);
    }

    @Test
    void parse_data_with_split_keyword() {
        List<String> actual = RegionParser.parse("완도군 및 해남군 일대, 속초 ~ 양양");
        assertThat(Arrays.asList("완도군", "해남군", "속초", "양양")).isEqualTo(actual);
    }
}