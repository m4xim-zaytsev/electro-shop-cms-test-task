package com.example.store_cms.repository;

import com.example.store_cms.model.dto.BestEmployeeDTO;
import com.example.store_cms.model.registry.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> , JpaSpecificationExecutor<Purchase> {
    @Query("SELECT SUM(p.electroItem.price * p.electroItem.count) " +
            "FROM Purchase p " +
            "WHERE p.purchaseType.name = 'Наличные'")
    Long findTotalCashPayments();
}

