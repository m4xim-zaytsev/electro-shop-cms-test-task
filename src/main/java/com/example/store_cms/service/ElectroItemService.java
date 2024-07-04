package com.example.store_cms.service;

import com.example.store_cms.model.registry.ElectroItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ElectroItemService {
    List<ElectroItem> findAll();
    Page<ElectroItem> getAllElectroItemPageable(Integer offset, Integer limit);
    ElectroItem create(ElectroItem electroItem, Long electroTypeId, Long shopId,
                       Integer countLast);
    ElectroItem getById(Long id);
    ElectroItem update(Long id, ElectroItem electroItem, Long electroTypeId);
    void delete(Long id);
}
