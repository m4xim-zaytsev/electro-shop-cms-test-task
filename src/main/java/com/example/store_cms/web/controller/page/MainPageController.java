package com.example.store_cms.web.controller.page;

import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.model.registry.Purchase;
import com.example.store_cms.service.ElectroItemService;
import com.example.store_cms.service.EmployeeService;
import com.example.store_cms.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class MainPageController {

    private final EmployeeService employeeService;
    private final PurchaseService purchaseService;
    private final ElectroItemService electroItemService;

    @GetMapping
    public String mainPage(Model model) {

        return "index";
    }

    @GetMapping("/electrotovary")
    @ResponseBody
    public List<ElectroItem> getElectroItems() {
        return electroItemService.findAll();
    }

    @GetMapping("/pokupki")
    @ResponseBody
    public List<Purchase> getPurchases() {
        return purchaseService.findAll();
    }

    @GetMapping("/sotrudniki")
    @ResponseBody
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

}
