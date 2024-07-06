package com.example.store_cms.mapper;

import com.example.store_cms.model.directory.ElectroEmployee;
import com.example.store_cms.web.request.ElectroEmployeeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ElectroEmployeeMapper {

    @Mappings({
            @Mapping(source = "employeeId", target = "employee.id"),
            @Mapping(source = "electroTypeId", target = "electroType.id")
    })
    ElectroEmployee requestToElectroEmployee(ElectroEmployeeRequest request);
}
