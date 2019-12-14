package com.kakaopay.tourism.repository;

import java.util.Optional;

import com.kakaopay.tourism.domain.Theme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Optional<Theme> findByName(String theme);
}
