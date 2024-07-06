package com.example.store_cms.service;

import com.example.store_cms.model.directory.ElectroType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ElectroTypeService {
    Page<ElectroType> getAllElectroTypePageable(Integer offset, Integer limit);
    List<ElectroType> findAll();
    ElectroType create(ElectroType electroType);
    ElectroType getById(Long id);
    ElectroType update(Long id, ElectroType electroType);
    void delete(Long id);

}
