package com.example.store_cms.web.controller;

import com.example.store_cms.mapper.*;
import com.example.store_cms.model.directory.*;
import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.model.registry.Purchase;
import com.example.store_cms.service.*;
import com.example.store_cms.web.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class MainPageController {

    private final EmployeeService employeeService;
    private final PurchaseService purchaseService;
    private final ElectroItemService electroItemService;
    private final PositionTypeService positionTypeService;
    private final PurchaseTypeService purchaseTypeService;
    private final ElectroTypeService electroTypeService;
    private final ShopService shopService;

    private final ShopMapper shopMapper;
    private final EmployeeMapper employeeMapper;
    private final PurchaseMapper purchaseMapper;
    private final ElectroItemMapper electroItemMapper;
    private final PositionTypeMapper positionTypeMapper;
    private final PurchaseTypeMapper purchaseTypeMapper;

    @GetMapping
    public String mainPage() {
        return "index";
    }

    @GetMapping("/electrotovary")
    @ResponseBody
    public List<ElectroItemResponse> getElectroTovary(@RequestParam Integer offset, @RequestParam Integer limit) {
        return electroItemService.getAllElectroItemPageable(offset, limit).getContent()
                .stream().map(electroItemMapper::electroItemToResponse).collect(Collectors.toList());
    }

    @GetMapping("/sotrudniki")
    @ResponseBody
    public List<EmployeeResponse> getSotrudniki(@RequestParam Integer offset, @RequestParam Integer limit) {
        return employeeService.getAllEmployeePageable(offset, limit).getContent()
                .stream().map(employeeMapper::employeeToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/pokupki")
    @ResponseBody
    public List<PurchaseResponse> getPokupki(@RequestParam Integer offset, @RequestParam Integer limit) {
        return purchaseService.getAllPurchasePageable(offset, limit).getContent()
                .stream().map(purchaseMapper::purchaseToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/references/position_type")
    @ResponseBody
    public List<PositionTypeResponse> getPositionType(@RequestParam Integer offset, @RequestParam Integer limit) {
        return positionTypeService.getAllPositionTypePageable(offset, limit).getContent()
                .stream().map(positionTypeMapper::positionTypeToResponse).collect(Collectors.toList());
    }

    @GetMapping("/references/purchase_type")
    @ResponseBody
    public List<PurchaseTypeResponse> getPurchaseType(@RequestParam Integer offset, @RequestParam Integer limit) {
        return purchaseTypeService.getAllPurchaseTypePageable(offset, limit).getContent()
                .stream().map(purchaseTypeMapper::purchaseTypeToResponse).collect(Collectors.toList());
    }

    @GetMapping("/references/electro_type")
    @ResponseBody
    public List<ElectroType> getElectroType(@RequestParam Integer offset, @RequestParam Integer limit) {
        return electroTypeService.getAllElectroTypePageable(offset, limit).getContent();
    }

    @GetMapping("/references/shop")
    @ResponseBody
    public List<ShopResponse> getShop(@RequestParam Integer offset, @RequestParam Integer limit) {
        return shopService.getAllShopPageable(offset, limit).getContent().stream()
                .map(shopMapper::shopToResponse).collect(Collectors.toList());
    }

    @DeleteMapping("/references/shop/delete/{id}")
    public void deleteShop(@PathVariable("id") Long id) {
        shopService.delete(id);
    }
}
