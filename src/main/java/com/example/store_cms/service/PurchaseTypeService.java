package com.example.store_cms.service;

import com.example.store_cms.model.directory.ElectroEmployee;
import com.example.store_cms.model.directory.PurchaseType;
import org.springframework.data.domain.Page;

public interface PurchaseTypeService {
    Page<PurchaseType> getAllPurchaseTypePageable(Integer offset, Integer limit);
    PurchaseType create(PurchaseType purchaseType);
    PurchaseType getById(Long id);
    PurchaseType update(Long id, PurchaseType purchaseType);
    void delete(Long id);
}
