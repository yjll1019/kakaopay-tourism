package com.kakaopay.tourism.service.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThemeParser {
    public static final String DATA_SEPARATOR = ",";

    public static List<String> parse(String subRegions) {
        return Arrays.stream(subRegions.split(DATA_SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
