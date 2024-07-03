package com.example.store_cms.repository;

import com.example.store_cms.model.registry.ElectroItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectroItemRepository extends JpaRepository<ElectroItem, Long> {}

