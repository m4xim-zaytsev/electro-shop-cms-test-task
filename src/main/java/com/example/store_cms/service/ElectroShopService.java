package com.example.store_cms.service;

import com.example.store_cms.model.directory.ElectroShop;

public interface ElectroShopService {
    ElectroShop save(ElectroShop electroShop);
    ElectroShop findByElectroItemId(Long id);
}
