package com.kakaopay.tourism.domain.generator;

public class RegionIdGenerator implements IdGenerator {
    @Override
    public String getQuery() {
        return "select id from region where id = ?";
    }

    @Override
    public String getPrefix() {
        return "region";
    }
}
