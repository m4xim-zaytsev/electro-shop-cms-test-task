package com.example.store_cms.mapper;

import com.example.store_cms.model.dto.BestEmployeeDTO;
import com.example.store_cms.model.registry.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeDTOMapper {

    EmployeeDTOMapper INSTANCE = Mappers.getMapper(EmployeeDTOMapper.class);

    @Mapping(source = "employee.id", target = "id")
    @Mapping(source = "employee", target = "fullName", qualifiedByName = "fullName")
    @Mapping(source = "itemsSold", target = "itemsSold")
    @Mapping(source = "totalSales", target = "totalSales")
    @Mapping(source = "employee.positionType.name", target = "position")
    BestEmployeeDTO toDTO(Employee employee, Long totalSales, Long itemsSold);

    @Named("fullName")
    default String getFullName(Employee employee) {
        return employee.getFirstName() + " " +
                (employee.getPatronymic() != null ? employee.getPatronymic() + " " : "") +
                employee.getLastName();
    }
}
