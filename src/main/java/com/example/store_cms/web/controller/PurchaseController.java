package com.example.store_cms.web.controller;

import com.example.store_cms.mapper.PurchaseMapper;
import com.example.store_cms.model.registry.Purchase;
import com.example.store_cms.service.*;
import com.example.store_cms.web.request.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/pokupki")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final ElectroItemService electroItemService;
    private final EmployeeService employeeService;
    private final ShopService shopService;
    private final PurchaseTypeService purchaseTypeService;
    private final PurchaseMapper purchaseMapper;
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("purchaseRequest", new PurchaseRequest());
        model.addAttribute("electroItems", electroItemService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("shops", shopService.findAll());
        model.addAttribute("purchaseTypes", purchaseTypeService.findAll());
        return "create-purchase";
    }

    @PostMapping("/create")
    public String createPurchase(@ModelAttribute("purchaseRequest") @Valid PurchaseRequest purchaseRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("electroItems", electroItemService.findAll());
            model.addAttribute("employees", employeeService.findAll());
            model.addAttribute("shops", shopService.findAll());
            model.addAttribute("purchaseTypes", purchaseTypeService.findAll());
            return "create-purchase";
        }

        purchaseService.create(purchaseMapper.requestToPurchase(purchaseRequest),
                purchaseRequest.getElectroItemId(), purchaseRequest.getEmployeeId(), purchaseRequest.getPurchaseTypeId(), purchaseRequest.getShopId());
        return "redirect:/api/v1/main";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Purchase purchase = purchaseService.getById(id);
        PurchaseRequest request = new PurchaseRequest();
        model.addAttribute("purchase", purchaseMapper.purchaseToRequest(purchase));
        model.addAttribute("electroItems", electroItemService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("shops", shopService.findAll());
        model.addAttribute("purchaseTypes", purchaseTypeService.findAll());
        return "edit_purchases";
    }

    @PostMapping("/edit/{id}")
    public String editPurchase(@PathVariable("id") Long id, @ModelAttribute("purchase") @Valid PurchaseRequest purchase
            , BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("electroItems", electroItemService.findAll());
            model.addAttribute("employees", employeeService.findAll());
            model.addAttribute("shops", shopService.findAll());
            model.addAttribute("purchaseTypes", purchaseTypeService.findAll());
            return "edit_purchases";
        }
        purchaseService.update(id, purchaseMapper.requestToPurchase(purchase),
                purchase.getElectroItemId(), purchase.getEmployeeId(), purchase.getPurchaseTypeId()
                , purchase.getShopId());
        return "redirect:/api/v1/main";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        purchaseService.delete(id);
        return ResponseEntity.ok().build();
    }
}