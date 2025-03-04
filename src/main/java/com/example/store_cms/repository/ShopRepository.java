package com.example.store_cms.repository;

import com.example.store_cms.model.directory.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>
        , JpaSpecificationExecutor<Shop> {
    Boolean existsByName(String name);
    Boolean existsByNameAndAddress(String name, String address);
}

