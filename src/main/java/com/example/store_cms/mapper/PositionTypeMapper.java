package com.example.store_cms.mapper;

import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.web.request.PositionTypeRequest;
import com.example.store_cms.web.response.PositionTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PositionTypeMapper {

    PositionTypeResponse positionTypeToResponse(PositionType positionType);

    PositionType requestToPositionType(PositionTypeRequest request);
}
