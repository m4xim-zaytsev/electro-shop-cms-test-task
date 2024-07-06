package com.example.store_cms.web.controller;

import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.service.PurchaseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/references/purchasetypes")
@RequiredArgsConstructor
public class PurchaseTypeController {

    private final PurchaseTypeService purchaseTypeService;

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        PurchaseType purchaseType = purchaseTypeService.getById(id);
        model.addAttribute("purchaseType", purchaseType);
        return "edit_purchasetype";
    }

    @PostMapping("/edit/{id}")
    public String editPurchaseType(@PathVariable("id") Long id, @ModelAttribute("purchaseType") PurchaseType purchaseType) {
        purchaseTypeService.update(id, purchaseType);
        return "redirect:/api/v1/main";
    }
}
