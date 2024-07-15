package com.example.store_cms.service;

import com.example.store_cms.model.dto.BestEmployeeDTO;

import java.util.List;

public interface FilterService {
    public List<Object[]> getBestEmployees();
    List<Object[]> findBestEmployeesSales();
    List<Object[]> getBestJuniorSalespersonForSmartWatches();
}
