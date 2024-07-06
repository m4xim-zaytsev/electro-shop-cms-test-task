package com.example.store_cms.web.controller;

import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.service.ElectroTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/references/electrotypes")
@RequiredArgsConstructor
public class ElectroTypeController {

    private final ElectroTypeService electroTypeService;

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        ElectroType electroType = electroTypeService.getById(id);
        model.addAttribute("electroType", electroType);
        return "edit_electrotype";
    }

    @PostMapping("/edit/{id}")
    public String editElectroType(@PathVariable("id") Long id, @ModelAttribute("electroType") ElectroType electroType) {
        electroTypeService.update(id, electroType);
        return "redirect:/api/v1/main";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        electroTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}