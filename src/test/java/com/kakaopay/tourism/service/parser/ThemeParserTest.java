package com.kakaopay.tourism.service.parser;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ThemeParserTest {
    @Test
    void parse_data() {
        List<String> expected = Arrays.asList("농어촌생태체험", "문화생태체험", "자연생태체험");
        List<String> actual = ThemeParser.parse("농어촌생태체험,문화생태체험,자연생태체험,");

        assertThat(expected).isEqualTo(actual);
    }
}