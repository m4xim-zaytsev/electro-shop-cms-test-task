package com.example.store_cms.service.impl;

import com.example.store_cms.exception.EntityNotFoundException;
import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.repository.ShopRepository;
import com.example.store_cms.service.ShopService;
import com.example.store_cms.utility.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

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
        return shopRepository.save(shop);
    }

    @Override
    public Shop getById(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        MessageFormat.format("shop with id {0} not found", id)));
    }

    @Override
    public Shop update(Long id, Shop shop) {
        Shop toUpdate = getById(id);
        BeanUtils.copyProperties(toUpdate,shop);
        return shopRepository.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }
}
