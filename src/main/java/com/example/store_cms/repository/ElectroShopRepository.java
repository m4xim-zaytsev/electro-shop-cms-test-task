package com.example.store_cms.repository;

import com.example.store_cms.model.directory.ElectroShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectroShopRepository  extends JpaRepository<ElectroShop, Long>
        , JpaSpecificationExecutor<ElectroShop> {

    ElectroShop findByElectroItemId(Long id);
}
