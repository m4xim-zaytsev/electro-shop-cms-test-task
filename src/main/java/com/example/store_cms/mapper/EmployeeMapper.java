package com.example.store_cms.mapper;

import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.web.request.EmployeeRequest;
import com.example.store_cms.web.response.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ShopMapper.class, PositionTypeMapper.class})
public interface EmployeeMapper {

    @Mapping(source = "shop.id", target = "shopResponse.id")
    @Mapping(source = "positionType.id", target = "positionTypeResponse.id")
    EmployeeResponse employeeToResponse(Employee employee);

    @Mapping(source = "shopId", target = "shop.id")
    @Mapping(source = "positionId", target = "positionType.id")
    Employee requestToEmployee(EmployeeRequest request);
}
