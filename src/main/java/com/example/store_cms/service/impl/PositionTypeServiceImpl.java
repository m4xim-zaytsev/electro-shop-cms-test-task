package com.example.store_cms.service.impl;

import com.example.store_cms.exception.BadRequestException;
import com.example.store_cms.exception.EntityNotFoundException;
import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.repository.PositionTypeRepository;
import com.example.store_cms.service.PositionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service
@RequiredArgsConstructor
public class PositionTypeServiceImpl implements PositionTypeService {

    private final PositionTypeRepository positionTypeRepository;

    @Override
    public Page<PositionType> getAllPositionTypePageable(Integer offset, Integer limit) {
        Pageable nexPage = PageRequest.of(offset,limit);
        return positionTypeRepository.findAll(nexPage);
    }

    @Override
    public PositionType create(PositionType positionType) {
        if (positionTypeRepository.existsByName(positionType.getName()))
            throw new BadRequestException(MessageFormat.format(
                    "positionType {0} already exist",positionType.getName()
            ));
        return positionTypeRepository.save(positionType);
    }

    @Override
    public PositionType findByName(String name) {
        return positionTypeRepository.findByName(name)
                .orElseThrow(()->new EntityNotFoundException(
                        MessageFormat.format("positionType with name {0} not found", name)));
    }

    @Override
    public PositionType getById(Long id) {
        return null;
    }

    @Override
    public PositionType update(Long id, PositionType positionType) {
        return null;
    }


    @Override
    public void delete(Long id) {

    }
}
