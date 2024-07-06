package com.example.store_cms.repository;

import com.example.store_cms.model.directory.PositionType;
import liquibase.license.LicenseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionTypeRepository extends JpaRepository<PositionType, Long>
        , JpaSpecificationExecutor<PositionType> {
    List<PositionType> findAll();
    Optional<PositionType> findByName(String name);
    Boolean existsByName(String name);
}

