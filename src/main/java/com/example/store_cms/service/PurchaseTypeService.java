package com.example.store_cms.service;

import com.example.store_cms.model.directory.PurchaseType;

public interface PurchaseTypeService {
    PurchaseType create(PurchaseType purchaseType);
    PurchaseType getById(Long id);
    PurchaseType update(Long id, PurchaseType purchaseType);
    void delete(Long id);
}
