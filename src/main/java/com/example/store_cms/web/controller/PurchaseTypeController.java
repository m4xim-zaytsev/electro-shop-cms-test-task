package com.example.store_cms.web.controller;

import com.example.store_cms.mapper.PurchaseTypeMapper;
import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.service.PurchaseTypeService;
import com.example.store_cms.web.request.PurchaseTypeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/references/purchase_type")
@RequiredArgsConstructor
public class PurchaseTypeController {

    private final PurchaseTypeService purchaseTypeService;
    private final PurchaseTypeMapper purchaseTypeMapper;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("purchaseTypeRequest", new PurchaseTypeRequest());
        return "create-purchase-type";
    }

    @PostMapping("/create")
    public String createPurchaseType(@ModelAttribute("purchaseTypeRequest") @Valid PurchaseTypeRequest purchaseTypeRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "create-purchase-type";
        }
        purchaseTypeService.create(purchaseTypeMapper.requestToPurchaseType(purchaseTypeRequest));
        return "redirect:/api/v1/main";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        PurchaseType purchaseType = purchaseTypeService.getById(id);
        model.addAttribute("purchaseType", purchaseType);
        return "edit_purchasetype";
    }

    @PostMapping("/edit/{id}")
    public String editPurchaseType(@PathVariable("id") Long id, @ModelAttribute("purchaseType") PurchaseTypeRequest purchaseType) {
        purchaseTypeService.update(id, purchaseTypeMapper.requestToPurchaseType(purchaseType));
        return "redirect:/api/v1/main";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        purchaseTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
