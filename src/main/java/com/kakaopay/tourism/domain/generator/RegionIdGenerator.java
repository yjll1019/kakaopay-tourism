package com.kakaopay.tourism.domain.generator;

public class RegionIdGenerator implements IdGenerator {
    @Override
    public String getQuery() {
        return "select count(*) from Region where id like %s";
    }

    @Override
    public String getPrefix() {
        return "region";
    }
}
