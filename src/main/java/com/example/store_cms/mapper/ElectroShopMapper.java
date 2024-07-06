package com.example.store_cms.mapper;

import com.example.store_cms.model.directory.ElectroShop;
import com.example.store_cms.web.request.ElectroShopRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ElectroShopMapper {

    @Mappings({
            @Mapping(source = "shopId", target = "shop.id"),
            @Mapping(source = "electroItemId", target = "electroItem.id")
    })
    ElectroShop requestToElectroShop(ElectroShopRequest request);}
