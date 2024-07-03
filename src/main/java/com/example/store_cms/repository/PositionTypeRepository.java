package com.example.store_cms.repository;


import com.example.store_cms.model.directory.PositionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionTypeRepository extends JpaRepository<PositionType, Long> {
    Optional<PositionType> findByName(String name);
    Boolean existsByName(String name);
}

