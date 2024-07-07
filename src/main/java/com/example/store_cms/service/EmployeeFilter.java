package com.example.store_cms.service;

import com.example.store_cms.model.registry.Employee;

import java.util.List;

public interface EmployeeFilter {
    List<Employee> findEmployeesWithTotalCountProductsSold(Long positionTypeId);
    List<Employee> findEmployeesWithTotalPriceProductsSold(Long positionTypeId);
    Employee getTopBySmartWatch();
}
