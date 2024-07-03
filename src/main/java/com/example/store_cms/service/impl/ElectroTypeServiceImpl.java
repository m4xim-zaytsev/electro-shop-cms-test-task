package com.example.store_cms.service.impl;

import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.repository.ElectroTypeRepository;
import com.example.store_cms.service.ElectroTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectroTypeServiceImpl implements ElectroTypeService {

    private final ElectroTypeRepository electroTypeRepository;

    @Override
    public Page<ElectroType> getAllElectroTypePageable(Integer offset, Integer limit) {
        Pageable nexPage = PageRequest.of(offset,limit);
        return electroTypeRepository.findAll(nexPage);
    }

    @Override
    public ElectroType create(ElectroType electroType) {
        return null;
    }

    @Override
    public ElectroType getById(Long id) {
        return null;
    }

    @Override
    public ElectroType update(Long id, ElectroType electroType) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
