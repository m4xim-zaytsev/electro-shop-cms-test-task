package com.example.store_cms.service.impl;

import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.repository.ShopRepository;
import com.example.store_cms.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    @Override
    public Page<Shop> getAllShopPageable(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return shopRepository.findAll(nextPage);
    }

    @Override
    public Shop create(Shop shop) {
        return null;
    }

    @Override
    public Shop getById(Long id) {
        return null;
    }

    @Override
    public Shop update(Long id, Shop shop) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
