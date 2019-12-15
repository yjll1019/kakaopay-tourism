package com.kakaopay.tourism.service.parser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegionParser {
    public static final String DATA_SEPARATOR = ",";

    private static Pattern REMOVE_KEYWORD = Pattern.compile("(\\s+)?(등|일대|일원|번지|\\n)(\\s*)");

    private static Pattern SPLIT_KEYWORD = Pattern.compile("(\\s*)([및|~]+)(\\s*)");

    public static List<String> parse(String regions) {
        regions = REMOVE_KEYWORD.matcher(regions).replaceAll("").trim();
        regions = SPLIT_KEYWORD.matcher(regions).replaceAll(DATA_SEPARATOR).trim();

        return Arrays.stream(regions.split(DATA_SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
