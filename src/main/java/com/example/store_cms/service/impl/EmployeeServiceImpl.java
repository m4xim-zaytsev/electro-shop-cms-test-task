package com.example.store_cms.service.impl;

import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.repository.EmployeeRepository;
import com.example.store_cms.repository.PositionTypeRepository;
import com.example.store_cms.service.EmployeeService;
import com.example.store_cms.service.PositionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionTypeService positionTypeService;

    @Override
    public Employee create(Employee employee, String positionType) {

        PositionType type = positionTypeService.findByName(positionType);
        employee.setPositionType(type);

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> getAllEmployeePageable(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return employeeRepository.findAll(nextPage);
    }

    @Override
    public Employee getById(Long id) {
        return null;
    }

    @Override
    public Employee update(Long id, Employee employee) {
        return null;
    }



    @Override
    public void delete(Long id) {

    }
}
