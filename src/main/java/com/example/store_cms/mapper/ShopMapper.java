package com.example.store_cms.mapper;

import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.web.request.ShopRequest;
import com.example.store_cms.web.response.ShopResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopMapper {

    ShopResponse shopToResponse(Shop shop);
    ShopRequest shopToRequest(Shop shop);
    Shop requestToShop(ShopRequest request);
}
