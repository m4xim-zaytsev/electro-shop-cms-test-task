package com.example.store_cms.repository;

import com.example.store_cms.model.directory.ElectroEmployee;
import com.example.store_cms.model.registry.ElectroItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectroEmployeeRepository
        extends JpaRepository<ElectroEmployee, Long>
        , JpaSpecificationExecutor<ElectroEmployee> {
}
