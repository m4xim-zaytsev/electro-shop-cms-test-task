package com.example.store_cms.service;

import com.example.store_cms.model.directory.ElectroType;

public interface ElectroTypeService {
    ElectroType create(ElectroType electroType);
    ElectroType getById(Long id);
    ElectroType update(Long id, ElectroType electroType);
    void delete(Long id);

}
