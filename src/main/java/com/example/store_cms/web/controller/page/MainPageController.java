package com.example.store_cms.web.controller.page;

import com.example.store_cms.model.directory.*;
import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.model.registry.Purchase;
import com.example.store_cms.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public String mainPage(Model model) {

        return "index";
    }

    @GetMapping("/electrotovary")
    @ResponseBody
    public List<ElectroItem> getElectroTovary(@RequestParam Integer offset, @RequestParam Integer limit) {
        return electroItemService.getAllElectroItemPageable(offset, limit).getContent();
    }

    @GetMapping("/sotrudniki")
    @ResponseBody
    public List<Employee> getSotrudniki(@RequestParam Integer offset, @RequestParam Integer limit) {
        return employeeService.getAllEmployeePageable(offset, limit).getContent();
    }

    @GetMapping("/pokupki")
    @ResponseBody
    public List<Purchase> getPokupki(@RequestParam Integer offset, @RequestParam Integer limit) {
        return purchaseService.getAllPurchasePageable(offset, limit).getContent();
    }

    @GetMapping("/references/position_type")
    @ResponseBody
    public List<PositionType> getPositionType(@RequestParam Integer offset, @RequestParam Integer limit) {
        return positionTypeService.getAllPositionTypePageable(offset, limit).getContent();
    }

    @GetMapping("/references/purchase_type")
    @ResponseBody
    public List<PurchaseType> getPurchaseType(@RequestParam Integer offset, @RequestParam Integer limit) {
        return purchaseTypeService.getAllPurchaseTypePageable(offset, limit).getContent();
    }

    @GetMapping("/references/electro_type")
    @ResponseBody
    public List<ElectroType> getElectroType(@RequestParam Integer offset, @RequestParam Integer limit) {
        return electroTypeService.getAllElectroTypePageable(offset, limit).getContent();
    }

    @GetMapping("/references/shop")
    @ResponseBody
    public List<Shop> getShop(@RequestParam Integer offset, @RequestParam Integer limit) {
        return shopService.getAllShopPageable(offset, limit).getContent();
    }
}
