package com.kakaopay.tourism.service;

import com.kakaopay.tourism.domain.Theme;
import com.kakaopay.tourism.repository.ThemeRepository;

import org.springframework.stereotype.Service;

@Service
public class ThemeService {
    private ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public Theme save(String nameOfTheme) {
        return themeRepository.findByName(nameOfTheme)
                .orElseGet(() -> themeRepository.save(new Theme(nameOfTheme)));
    }
}
