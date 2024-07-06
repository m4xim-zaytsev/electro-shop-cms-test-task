package com.example.store_cms.service.impl;

import com.example.store_cms.model.directory.ElectroShop;
import com.example.store_cms.repository.ElectroShopRepository;
import com.example.store_cms.service.ElectroShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectroShopServiceImpl implements ElectroShopService {

    private final ElectroShopRepository electroShopRepository;

    @Override
    public ElectroShop save(ElectroShop electroShop) {
        return electroShopRepository.save(electroShop);
    }

    @Override
    public ElectroShop findByElectroItemId(Long id) {
        return findByElectroItemId(id);
    }
}
