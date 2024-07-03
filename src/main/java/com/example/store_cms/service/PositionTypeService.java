package com.example.store_cms.service;

import com.example.store_cms.model.directory.PositionType;

public interface PositionTypeService {
    PositionType create(PositionType positionType);
    PositionType getById(Long id);
    PositionType update(Long id, PositionType positionType);
    PositionType findByName(String name);
    void delete(Long id);
}
