package com.example.store_cms.service;

import com.example.store_cms.model.directory.Shop;

public interface ShopService {
    Shop create(Shop shop);
    Shop getById(Long id);
    Shop update(Long id, Shop shop);
    void delete(Long id);
}
