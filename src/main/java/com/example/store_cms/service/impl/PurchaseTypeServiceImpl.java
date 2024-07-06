package com.example.store_cms.service.impl;

import com.example.store_cms.exception.EntityNotFoundException;
import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.repository.PurchaseTypeRepository;
import com.example.store_cms.service.PurchaseTypeService;
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
public class PurchaseTypeServiceImpl implements PurchaseTypeService {

    private final PurchaseTypeRepository purchaseTypeRepository;

    @Override
    public Page<PurchaseType> getAllPurchaseTypePageable(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return purchaseTypeRepository.findAll(nextPage);
    }

    @Override
    public List<PurchaseType> findAll() {
        return purchaseTypeRepository.findAll();
    }

    @Override
    public PurchaseType create(PurchaseType purchaseType) {
        return purchaseTypeRepository.save(purchaseType);
    }

    @Override
    public PurchaseType getById(Long id) {
        return purchaseTypeRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        MessageFormat.format("purchaseType with id {0} not found", id)));
    }

    @Override
    public PurchaseType update(Long id, PurchaseType purchaseType) {
        PurchaseType existing = getById(id);
        BeanUtils.copyProperties(existing, purchaseType);
        return purchaseTypeRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        purchaseTypeRepository.deleteById(id);

    }
}
