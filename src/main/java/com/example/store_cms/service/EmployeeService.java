package com.example.store_cms.service;

import com.example.store_cms.model.registry.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee create(Employee employee,Long positionTypeId, Long shopId);
    Employee getById(Long id);
    Employee update(Long id, Employee employee, Long shopId, String positionType);
    Page<Employee> getAllEmployeePageable(Integer offset,Integer limit);
    void delete(Long id);
}
