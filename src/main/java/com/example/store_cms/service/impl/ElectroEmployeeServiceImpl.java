package com.example.store_cms.service.impl;

import com.example.store_cms.model.directory.ElectroEmployee;
import com.example.store_cms.repository.ElectroEmployeeRepository;
import com.example.store_cms.service.ElectroEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectroEmployeeServiceImpl implements ElectroEmployeeService {

    private final ElectroEmployeeRepository employeeRepository;

    @Override
    public ElectroEmployee save(ElectroEmployee electroEmployee) {
        return employeeRepository.save(electroEmployee);
    }
}
