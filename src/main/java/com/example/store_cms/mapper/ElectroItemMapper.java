package com.example.store_cms.mapper;

import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.web.request.ElectroItemRequest;
import com.example.store_cms.web.response.ElectroItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ElectroTypeMapper.class})
public interface ElectroItemMapper {

    @Mapping(source = "electroType.id", target = "electroTypeResponse.id")
    ElectroItemResponse electroItemToResponse(ElectroItem electroItem);

    @Mapping(source = "electroTypeId", target = "electroType.id")
    ElectroItem requestToElectroItem(ElectroItemRequest request);

    @Mapping(source = "electroType.id", target = "electroTypeId")
    ElectroItemRequest electroItemToRequest(ElectroItem electroItem);
}
