package com.example.store_cms.service.impl;

import com.example.store_cms.exception.EntityNotFoundException;
import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.model.registry.Purchase;
import com.example.store_cms.repository.PurchaseRepository;
import com.example.store_cms.service.*;
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
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ElectroItemService electroItemService;
    private final EmployeeService employeeService;
    private final PurchaseTypeService purchaseTypeService;
    private final ShopService shopService;

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
    public Purchase create(Purchase purchase, Long electorId, Long employeeId,Long typeId, Long shopId) {
        ElectroItem electroItem = electroItemService.getById(electorId);
        Employee employee = employeeService.getById(employeeId);
        PurchaseType purchaseType = purchaseTypeService.getById(typeId);
        Shop shop = shopService.getById(shopId);

        purchase.setElectroItem(electroItem);
        purchase.setEmployee(employee);
        purchase.setPurchaseType(purchaseType);
        purchase.setShop(shop);
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase getById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        MessageFormat.format("purchase with id {0} not found", id)));
    }

    @Override
    public Purchase update(Long id, Purchase purchase
            , Long electorId, Long employeeId,Long typeId, Long shopId) {
        ElectroItem electroItem = electroItemService.getById(electorId);
        Employee employee = employeeService.getById(employeeId);
        PurchaseType purchaseType = purchaseTypeService.getById(typeId);
        Shop shop = shopService.getById(shopId);

        Purchase toUpdate = getById(id);
        BeanUtils.copyProperties(toUpdate,purchase);
        toUpdate.setElectroItem(electroItem);
        toUpdate.setEmployee(employee);
        toUpdate.setPurchaseType(purchaseType);
        toUpdate.setShop(shop);
        return purchaseRepository.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }
}
