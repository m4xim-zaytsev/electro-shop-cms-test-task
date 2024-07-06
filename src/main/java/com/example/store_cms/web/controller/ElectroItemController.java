package com.example.store_cms.web.controller;

import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.service.ElectroItemService;
import com.example.store_cms.service.ElectroTypeService;
import com.example.store_cms.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/electrotovary")
@RequiredArgsConstructor
public class ElectroItemController {

    private final ElectroItemService electroItemService;
    private final ElectroTypeService electroTypeService;

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        ElectroItem electroItem = electroItemService.getById(id);
        model.addAttribute("electroItem", electroItem);
        model.addAttribute("electroTypes", electroTypeService.findAll());
        return "edit_electroitem";
    }

    @PostMapping("/edit/{id}")
    public String updateElectroItem(@PathVariable("id") Long id,
                                    @ModelAttribute("electroItem") ElectroItem updatedElectroItem) {
        electroItemService.update(id, updatedElectroItem, updatedElectroItem.getElectroType().getId());
        return "redirect:/api/v1/main";
    }

}
