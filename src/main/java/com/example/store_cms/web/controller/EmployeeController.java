package com.example.store_cms.web.controller;


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
        Employee employee = new Employee();
        employee.setLastName(employeeRequest.getLastName());
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setPatronymic(employeeRequest.getPatronymic());
        employee.setBirthDate(employeeRequest.getBirthDate());
        employee.setGender(employeeRequest.getGender());
        employeeService.create(employee, employeeRequest.getPositionId(), employeeRequest.getShopId());
        return "redirect:/api/v1/main";
    }

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
