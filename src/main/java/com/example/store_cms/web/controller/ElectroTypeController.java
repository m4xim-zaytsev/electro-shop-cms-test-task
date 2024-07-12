package com.example.store_cms.web.controller;

import com.example.store_cms.mapper.ElectroTypeMapper;
import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.service.ElectroTypeService;
import com.example.store_cms.web.request.ElectroTypeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/references/electro_type")
@RequiredArgsConstructor
public class ElectroTypeController {

    private final ElectroTypeService electroTypeService;
    private final ElectroTypeMapper electroTypeMapper;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("electroTypeRequest", new ElectroTypeRequest());
        return "create-electrotype-form";
    }

    @PostMapping("/create")
    public String createElectroType(@ModelAttribute("electroTypeRequest") @Valid ElectroTypeRequest electroTypeRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "create-electrotype-form";
        }
        electroTypeService.create(electroTypeMapper.requestToElectroType(electroTypeRequest));
        return "redirect:/api/v1/main";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        ElectroType electroType = electroTypeService.getById(id);
        model.addAttribute("electroType", electroType);
        return "edit_electrotype";
    }

    @PostMapping("/edit/{id}")
    public String editElectroType(@PathVariable("id") Long id, @ModelAttribute("electroType") ElectroTypeRequest electroType) {
        electroTypeService.update(id, electroTypeMapper.requestToElectroType(electroType));
        return "redirect:/api/v1/main";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        electroTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}