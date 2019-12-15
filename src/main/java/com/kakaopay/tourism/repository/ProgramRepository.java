package com.kakaopay.tourism.repository;

import java.util.List;

import com.kakaopay.tourism.domain.Program;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByRegions_Id(String id);

    List<Program> findByIntroduceContaining(String introduceKeyword);
}
