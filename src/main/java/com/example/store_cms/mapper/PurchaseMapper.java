package com.example.store_cms.mapper;

import com.example.store_cms.model.registry.Purchase;
import com.example.store_cms.web.request.PurchaseRequest;
import com.example.store_cms.web.response.PurchaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ElectroItemMapper.class, EmployeeMapper.class, ShopMapper.class, PurchaseTypeMapper.class})
public interface PurchaseMapper {


    @Mapping(source = "electroItem.id", target = "electroItemResponse.id")
    @Mapping(source = "employee.id", target = "employeeResponse.id")
    @Mapping(source = "shop.id", target = "shopResponse.id")
    @Mapping(source = "purchaseType.id", target = "purchaseTypeResponse.id")
    PurchaseResponse purchaseToResponse(Purchase purchase);

    @Mapping(source = "electroItemId", target = "electroItem.id")
    @Mapping(source = "employeeId", target = "employee.id")
    @Mapping(source = "shopId", target = "shop.id")
    @Mapping(source = "purchaseTypeId", target = "purchaseType.id")
    Purchase requestToPurchase(PurchaseRequest request);

    @Mapping(source = "electroItem.id", target = "electroItemId")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "purchaseType.id", target = "purchaseTypeId")
    PurchaseRequest purchaseToRequest(Purchase purchase);
}
