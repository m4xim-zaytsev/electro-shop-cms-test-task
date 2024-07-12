package com.example.store_cms.web.controller;

import com.example.store_cms.mapper.PositionTypeMapper;
import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.service.PositionTypeService;
import com.example.store_cms.web.request.PositionTypeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/references/position_type")
@RequiredArgsConstructor
public class PositionTypeController {

    private final PositionTypeService positionTypeService;
    private final PositionTypeMapper positionTypeMapper;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("positionTypeRequest", new PositionTypeRequest());
        return "create-position-type";
    }

    @PostMapping("/create")
    public String createPositionType(@ModelAttribute("positionTypeRequest") @Valid PositionTypeRequest positionTypeRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "create-position-type";
        }
        positionTypeService.create(positionTypeMapper.requestToPositionType(positionTypeRequest));
        return "redirect:/api/v1/main";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        PositionType positionType = positionTypeService.getById(id);
        model.addAttribute("positionType", positionType);
        return "edit_positiontype";
    }

    @PostMapping("/edit/{id}")
    public String editPositionType(@PathVariable("id") Long id, @ModelAttribute("positionType") PositionTypeRequest positionType) {
        positionTypeService.update(id, positionTypeMapper.requestToPositionType(positionType));
        return "redirect:/api/v1/main";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        positionTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
