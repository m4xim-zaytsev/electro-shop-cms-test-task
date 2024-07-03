package com.example.store_cms.service.impl;

import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.repository.PurchaseTypeRepository;
import com.example.store_cms.service.PurchaseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseTypeServiceImpl implements PurchaseTypeService {

    private final PurchaseTypeRepository purchaseTypeRepository;

    @Override
    public Page<PurchaseType> getAllPurchaseTypePageable(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return purchaseTypeRepository.findAll(nextPage);
    }

    @Override
    public PurchaseType create(PurchaseType purchaseType) {
        return null;
    }

    @Override
    public PurchaseType getById(Long id) {
        return null;
    }

    @Override
    public PurchaseType update(Long id, PurchaseType purchaseType) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
