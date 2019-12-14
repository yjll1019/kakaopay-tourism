package com.kakaopay.tourism.service;

import java.util.List;
import java.util.stream.Collectors;

import com.kakaopay.tourism.domain.Theme;
import com.kakaopay.tourism.repository.ThemeRepository;
import com.kakaopay.tourism.service.parser.ThemeParser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ThemeService {
    private ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public Theme save(String nameOfTheme) {
        return themeRepository.findByName(nameOfTheme)
                .orElseGet(() -> themeRepository.save(new Theme(nameOfTheme)));
    }

    public List<Theme> saveThemes(String dataOfTheme) {
        List<String> themes = ThemeParser.parse(dataOfTheme);
        return themes.stream()
                .map(theme -> save(theme))
                .collect(Collectors.toList());
    }
}
