package com.example.store_cms.service;

import com.example.store_cms.model.directory.PositionType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PositionTypeService {
    Page<PositionType> getAllPositionTypePageable(Integer offset, Integer limit);
    PositionType create(PositionType positionType);
    PositionType getById(Long id);
    PositionType update(Long id, PositionType positionType);
    PositionType findByName(String name);
    void delete(Long id);
    List<PositionType> findAll();
    Boolean existByName(String name);
}
