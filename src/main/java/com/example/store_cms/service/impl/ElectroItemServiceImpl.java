package com.example.store_cms.service.impl;

import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.repository.ElectroItemRepository;
import com.example.store_cms.service.ElectroItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectroItemServiceImpl  implements ElectroItemService {

    private final ElectroItemRepository electroItemRepository;

    @Override
    public List<ElectroItem> findAll() {
        return electroItemRepository.findAll();
    }

    @Override
    public Page<ElectroItem> getAllElectroItemPageable(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return electroItemRepository.findAll(nextPage);
    }

    @Override
    public ElectroItem create(ElectroItem electroItem) {
        return null;
    }

    @Override
    public ElectroItem getById(Long id) {
        return null;
    }

    @Override
    public ElectroItem update(Long id, ElectroItem electroItem) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
