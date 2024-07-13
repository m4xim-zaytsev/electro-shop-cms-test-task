package com.example.store_cms.mapper;

import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.web.request.ElectroItemRequest;
import com.example.store_cms.web.request.ElectroTypeRequest;
import com.example.store_cms.web.response.ElectroTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ElectroTypeMapper {

    ElectroTypeResponse electroTypeToResponse(ElectroType electroType);
    ElectroType requestToElectroType(ElectroTypeRequest request);
    ElectroTypeRequest electroTypeToRequest(ElectroType electroType);
}
