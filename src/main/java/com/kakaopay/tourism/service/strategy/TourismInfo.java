package com.kakaopay.tourism.service.strategy;

public enum TourismInfo {
    COLUMN_THEME(5), COLUMN_INTRODUCE(3), COLUMN_DETAIL(1);

    private int score;

    TourismInfo(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}
