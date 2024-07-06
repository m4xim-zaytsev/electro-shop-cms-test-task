package com.example.store_cms.service.impl;

import com.example.store_cms.exception.EntityNotFoundException;
import com.example.store_cms.model.directory.ElectroShop;
import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.repository.ElectroItemRepository;
import com.example.store_cms.service.ElectroItemService;
import com.example.store_cms.service.ElectroShopService;
import com.example.store_cms.service.ElectroTypeService;
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
public class ElectroItemServiceImpl  implements ElectroItemService {

    private final ElectroItemRepository electroItemRepository;
    private final ElectroTypeService electroTypeService;
    private final ElectroShopService electroShopService;
    private final ShopService shopService;

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
    public ElectroItem create(ElectroItem electroItem, Long electroTypeId) {
        ElectroType electroType = electroTypeService.getById(electroTypeId);
        electroItem.setElectroType(electroType);
        ElectroItem saved = electroItemRepository.save(electroItem);
        return saved;
    }

    @Override
    public ElectroItem getById(Long id) {
        return electroItemRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        MessageFormat.format("electroItem with id {0} not found", id)));
    }

    @Override
    public ElectroItem update(Long id, ElectroItem electroItem, Long electroTypeId) {
        ElectroItem toUpdate= getById(id);
        ElectroType electroType = electroTypeService.getById(electroTypeId);
        BeanUtils.copyProperties(toUpdate,electroItem);
        toUpdate.setElectroType(electroType);
        return electroItemRepository.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        electroItemRepository.deleteById(id);
    }
}
