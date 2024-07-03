package com.example.store_cms.service;

import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.model.registry.Purchase;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAll();
    Page<Purchase> getAllPurchasePageable(Integer offset, Integer limit);

    Purchase create(Purchase purchase);
    Purchase getById(Long id);
    Purchase update(Long id, Purchase purchase);
    void delete(Long id);
}
