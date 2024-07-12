package com.example.store_cms.web.controller;

import com.example.store_cms.mapper.ElectroItemMapper;
import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.service.ElectroItemService;
import com.example.store_cms.service.ElectroTypeService;
import com.example.store_cms.service.ShopService;
import com.example.store_cms.web.request.ElectroItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/electrotovary")
@RequiredArgsConstructor
public class ElectroItemController {

    private final ElectroItemService electroItemService;
    private final ElectroTypeService electroTypeService;
    private final ElectroItemMapper electroItemMapper;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("electroItemRequest", new ElectroItemRequest());
        model.addAttribute("electroTypes", electroTypeService.findAll());
        return "create-electroitem";
    }

    @PostMapping("/create")
    public String createElectroItem(@ModelAttribute("electroItemRequest") @Valid ElectroItemRequest electroItemRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("electroTypes", electroTypeService.findAll());
            return "create-electroitem";
        }
        electroItemService.create(electroItemMapper.requestToElectroItem(electroItemRequest), electroItemRequest.getElectroTypeId());
        return "redirect:/api/v1/main";
    }

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        electroItemService.delete(id);
        return ResponseEntity.ok().build();
    }

}
