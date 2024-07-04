package com.example.store_cms.mapper;

import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.web.request.PurchaseTypeRequest;
import com.example.store_cms.web.response.PurchaseTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseTypeMapper {

    PurchaseTypeResponse purchaseTypeToResponse(PurchaseType purchaseType);

    PurchaseType requestToPurchaseType(PurchaseTypeRequest request);
}
