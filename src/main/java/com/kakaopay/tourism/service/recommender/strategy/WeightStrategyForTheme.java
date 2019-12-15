package com.kakaopay.tourism.service.recommender.strategy;

import java.util.Map;

public class WeightStrategyForTheme implements WeightStrategy {
    @Override
    public int calculate(Map<TourismInfo, Integer> columnAndCountOfKeyword) {
        return columnAndCountOfKeyword.entrySet()
                .stream().mapToInt(data -> data.getKey().getScore() * data.getValue())
                .sum();
    }
}
