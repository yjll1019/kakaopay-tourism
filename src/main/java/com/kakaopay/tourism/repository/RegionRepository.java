package com.kakaopay.tourism.repository;

import java.util.List;
import java.util.Optional;

import com.kakaopay.tourism.domain.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {
    Optional<Region> findByName(String name);

    List<Region> findByNameContaining(String name);
}