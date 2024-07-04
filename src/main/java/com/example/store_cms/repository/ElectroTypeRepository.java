package com.example.store_cms.repository;

import com.example.store_cms.model.directory.ElectroType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectroTypeRepository extends JpaRepository<ElectroType, Long>
        , JpaSpecificationExecutor<ElectroType> {}

