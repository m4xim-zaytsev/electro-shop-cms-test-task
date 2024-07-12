package com.example.store_cms.repository;

import com.example.store_cms.model.directory.PurchaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseTypeRepository extends JpaRepository<PurchaseType, Long>
        , JpaSpecificationExecutor<PurchaseType> {
    Boolean existsByName(String name);
}
