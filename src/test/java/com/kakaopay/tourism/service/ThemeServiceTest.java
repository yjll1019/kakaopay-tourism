package com.kakaopay.tourism.service;

import com.kakaopay.tourism.domain.Theme;
import com.kakaopay.tourism.repository.ThemeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class ThemeServiceTest {
    @InjectMocks
    private ThemeService themeService;

    @Mock
    private ThemeRepository themeRepository;

    @Test
    void save_theme() {
        String nameOfTheme = "문화생태체험";
        themeService.save(nameOfTheme);

        verify(themeRepository, Mockito.times(1)).findByName(nameOfTheme);
        verify(themeRepository, Mockito.times(1)).save(any());
    }
}