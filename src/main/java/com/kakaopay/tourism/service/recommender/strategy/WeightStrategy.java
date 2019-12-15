package com.kakaopay.tourism.service.recommender.strategy;

import java.util.Map;

public interface WeightStrategy {
    int calculate(Map<TourismInfo, Integer> columnAndCountOfKeyword);
}
