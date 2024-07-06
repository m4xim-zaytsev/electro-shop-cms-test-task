package com.example.store_cms.web.controller;

import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.service.PositionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/references/positiontypes")
@RequiredArgsConstructor
public class PositionTypeController {

    private final PositionTypeService positionTypeService;

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        PositionType positionType = positionTypeService.getById(id);
        model.addAttribute("positionType", positionType);
        return "edit_positiontype";
    }

    @PostMapping("/edit/{id}")
    public String editPositionType(@PathVariable("id") Long id, @ModelAttribute("positionType") PositionType positionType) {
        positionTypeService.update(id, positionType);
        return "redirect:/api/v1/main";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        positionTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
