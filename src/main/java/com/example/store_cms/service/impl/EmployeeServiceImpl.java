package com.example.store_cms.service.impl;

import com.example.store_cms.exception.EntityNotFoundException;
import com.example.store_cms.model.directory.ElectroEmployee;
import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.repository.EmployeeRepository;
import com.example.store_cms.service.*;
import com.example.store_cms.utility.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionTypeService positionTypeService;
    private final ElectroEmployeeService electroEmployeeService;
    private final ElectroTypeService electroTypeService;
    private final ShopService shopService;

    @Override
    public Employee create(Employee employee, Long positionTypeId, Long shopId, Long electroTypeId) {
        PositionType type = positionTypeService.getById(positionTypeId);
        Shop shop =shopService.getById(shopId);

        employee.setPositionType(type);
        employee.setShop(shop);
        Employee savedEmployee = employeeRepository.save(employee);

        ElectroEmployee electroEmployee = new ElectroEmployee();
        electroEmployee.setEmployee(savedEmployee);
        electroEmployee.setElectroType(electroTypeService.getById(electroTypeId));
        electroEmployeeService.save(electroEmployee);
        return savedEmployee;
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
        return employeeRepository.findById(id).orElseThrow(()->new EntityNotFoundException(
                MessageFormat.format("employee with id {0} not found", id)));
    }

    @Override
    public Employee update(Long id, Employee employee, Long shopId, String positionType) {
        PositionType type = positionTypeService.findByName(positionType);
        Shop shop =shopService.getById(shopId);
        Employee toUpdate = getById(id);
        BeanUtils.copyProperties(toUpdate,employee);
        toUpdate.setShop(shop);
        toUpdate.setPositionType(type);
        return employeeRepository.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
