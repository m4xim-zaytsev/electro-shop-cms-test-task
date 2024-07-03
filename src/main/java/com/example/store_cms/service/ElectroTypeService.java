package com.example.store_cms.service;

import com.example.store_cms.model.directory.ElectroEmployee;
import com.example.store_cms.model.directory.ElectroType;
import org.springframework.data.domain.Page;

public interface ElectroTypeService {
    Page<ElectroType> getAllElectroTypePageable(Integer offset, Integer limit);
    ElectroType create(ElectroType electroType);
    ElectroType getById(Long id);
    ElectroType update(Long id, ElectroType electroType);
    void delete(Long id);

}
