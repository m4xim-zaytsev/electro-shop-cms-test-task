package com.example.store_cms.web.controller;


import com.example.store_cms.mapper.EmployeeMapper;
import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.service.EmployeeService;
import com.example.store_cms.service.PositionTypeService;
import com.example.store_cms.service.ShopService;
import com.example.store_cms.web.request.EmployeeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sotrudniki")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PositionTypeService positionTypeService;
    private final ShopService shopService;
    private final EmployeeMapper employeeMapper;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employeeRequest", new EmployeeRequest());
        model.addAttribute("shops", shopService.findAll());
        model.addAttribute("positionTypes", positionTypeService.findAll());
        return "create-employee";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute("employeeRequest") @Valid EmployeeRequest employeeRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            model.addAttribute("shops", shopService.findAll());
            model.addAttribute("positionTypes", positionTypeService.findAll());
            return "create-employee";
        }

        employeeService.create(employeeMapper.requestToEmployee(employeeRequest), employeeRequest.getPositionId(), employeeRequest.getShopId());
        return "redirect:/api/v1/main";
    }

    @GetMapping("/edit/{id}")
    public String getEditEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getById(id);

        EmployeeRequest employeeRequest = employeeMapper.employeeToRequest(employee);
        model.addAttribute("employeeRequest", employeeRequest);
        model.addAttribute("positionTypes", positionTypeService.findAll());
        model.addAttribute("shops", shopService.findAll());

        return "edit_employee";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") Long id,
                                 @ModelAttribute("employeeRequest") @Valid EmployeeRequest employeeRequest,
                                 BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("positionTypes", positionTypeService.findAll());
            model.addAttribute("shops", shopService.findAll());
            return "edit_employee";
        }

        PositionType type = positionTypeService.getById(employeeRequest.getPositionId());
        employeeService.update(id, employeeMapper.requestToEmployee(employeeRequest), employeeRequest.getShopId(), type.getName());
        return "redirect:/api/v1/main";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
