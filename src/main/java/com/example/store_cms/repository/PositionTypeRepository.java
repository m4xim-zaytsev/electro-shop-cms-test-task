package com.example.store_cms.repository;


import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.model.directory.PositionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionTypeRepository extends JpaRepository<PositionType, Long>
        , JpaSpecificationExecutor<PositionType> {
    Optional<PositionType> findByName(String name);
    Boolean existsByName(String name);
}

