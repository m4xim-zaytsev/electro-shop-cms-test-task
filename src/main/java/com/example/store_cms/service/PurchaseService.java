package com.example.store_cms.service;

import com.example.store_cms.model.registry.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAll();
    Purchase create(Purchase purchase);
    Purchase getById(Long id);
    Purchase update(Long id, Purchase purchase);
    void delete(Long id);
}
