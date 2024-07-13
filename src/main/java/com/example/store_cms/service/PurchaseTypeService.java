package com.example.store_cms.service;

import com.example.store_cms.model.directory.PurchaseType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PurchaseTypeService {
    Page<PurchaseType> getAllPurchaseTypePageable(Integer offset, Integer limit);
    List<PurchaseType> findAll();
    PurchaseType create(PurchaseType purchaseType);
    PurchaseType getById(Long id);
    PurchaseType update(Long id, PurchaseType purchaseType);
    void delete(Long id);
    Boolean existByName(String name);

}
