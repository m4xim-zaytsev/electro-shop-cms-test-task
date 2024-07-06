package com.example.store_cms.web.controller;


import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.service.EmployeeService;
import com.example.store_cms.service.PositionTypeService;
import com.example.store_cms.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sotrudniki")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PositionTypeService positionTypeService;
    private final ShopService shopService;

    @GetMapping("/edit/{id}")
    public String getEditEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("positionTypes", positionTypeService.findAll());
        model.addAttribute("shops", shopService.findAll());
        return "edit_employee";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @ModelAttribute Employee employee) {
        employeeService.update(id, employee, employee.getShop().getId(), employee.getPositionType().getName());
        return "redirect:/api/v1/main";
    }
}
