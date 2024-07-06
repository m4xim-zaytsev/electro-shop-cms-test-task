package com.example.store_cms.service;

import com.example.store_cms.model.directory.Shop;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShopService {
    Page<Shop> getAllShopPageable(Integer offset, Integer limit);
    Shop create(Shop shop);
    Shop getById(Long id);
    Shop update(Long id, Shop shop);
    void delete(Long id);
    List<Shop> findAll();
}
