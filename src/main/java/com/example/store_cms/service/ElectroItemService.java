package com.example.store_cms.service;

import com.example.store_cms.model.registry.ElectroItem;

import java.util.List;

public interface ElectroItemService {
    List<ElectroItem> findAll();
    ElectroItem create(ElectroItem electroItem);
    ElectroItem getById(Long id);
    ElectroItem update(Long id, ElectroItem electroItem);
    void delete(Long id);
}
