package com.example.store_cms.service.impl;

import com.example.store_cms.model.registry.Purchase;
import com.example.store_cms.repository.PurchaseRepository;
import com.example.store_cms.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Page<Purchase> getAllPurchasePageable(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return purchaseRepository.findAll(nextPage);
    }

    @Override
    public Purchase create(Purchase purchase) {
        return null;
    }

    @Override
    public Purchase getById(Long id) {
        return null;
    }

    @Override
    public Purchase update(Long id, Purchase purchase) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
