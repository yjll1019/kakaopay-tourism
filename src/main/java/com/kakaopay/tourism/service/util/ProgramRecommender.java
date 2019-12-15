package com.kakaopay.tourism.service.util;

import java.util.*;

import com.kakaopay.tourism.domain.Program;
import com.kakaopay.tourism.service.strategy.TourismInfo;
import com.kakaopay.tourism.service.strategy.WeightStrategy;

import static com.kakaopay.tourism.service.strategy.TourismInfo.*;

public class ProgramRecommender {
    public static Program recommend(String keyword, List<Program> programs, WeightStrategy weightStrategy) {
        Map<Program, Integer> data = new HashMap<>();
        programs.forEach(program -> data.put(program, 0));

        for (Program program : programs) {
            Map<TourismInfo, Integer> countOfKeyword = new HashMap<>();
            countOfKeyword.put(COLUMN_THEME, program.countKeywordOfTheme(keyword));
            countOfKeyword.put(COLUMN_INTRODUCE, program.countKeyOfIntroduce(keyword));
            countOfKeyword.put(COLUMN_DETAIL, program.countOfKeyword(keyword));

            data.put(program, weightStrategy.calculate(countOfKeyword));
        }

        return Collections.max(data.entrySet(),
                Comparator.comparingInt(Map.Entry::getValue))
                .getKey();
    }
}
