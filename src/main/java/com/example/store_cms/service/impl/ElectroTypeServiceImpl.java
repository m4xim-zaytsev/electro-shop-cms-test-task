package com.example.store_cms.service.impl;

import com.example.store_cms.exception.EntityNotFoundException;
import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.repository.ElectroTypeRepository;
import com.example.store_cms.service.ElectroTypeService;
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
public class ElectroTypeServiceImpl implements ElectroTypeService {

    private final ElectroTypeRepository electroTypeRepository;

    @Override
    public Page<ElectroType> getAllElectroTypePageable(Integer offset, Integer limit) {
        Pageable nexPage = PageRequest.of(offset,limit);
        return electroTypeRepository.findAll(nexPage);
    }

    @Override
    public List<ElectroType> findAll() {
        return electroTypeRepository.findAll();
    }

    @Override
    public ElectroType create(ElectroType electroType) {
        return electroTypeRepository.save(electroType);
    }

    @Override
    public ElectroType getById(Long id) {
        return electroTypeRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        MessageFormat.format("electroType with id {0} not found", id)));
    }

    @Override
    public ElectroType update(Long id, ElectroType electroType) {
        ElectroType existing = getById(id);
        BeanUtils.copyProperties(existing, electroType);
        return electroTypeRepository.save(existing);    }

    @Override
    public void delete(Long id) {
        electroTypeRepository.deleteById(id);

    }
}
