package com.example.store_cms.web.controller;

import com.example.store_cms.mapper.*;
import com.example.store_cms.model.directory.*;
import com.example.store_cms.model.dto.BestEmployeeDTO;
import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.model.registry.Purchase;
import com.example.store_cms.repository.EmployeeRepository;
import com.example.store_cms.service.*;
import com.example.store_cms.web.request.ShopRequest;
import com.example.store_cms.web.response.*;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class MainPageController {

    private final EmployeeService employeeService;
    private final PurchaseService purchaseService;
    private final ElectroItemService electroItemService;
    private final PositionTypeService positionTypeService;
    private final PurchaseTypeService purchaseTypeService;
    private final ElectroTypeService electroTypeService;
    private final ShopService shopService;
    private final FilterService filterService;

    private final ShopMapper shopMapper;
    private final EmployeeMapper employeeMapper;
    private final PurchaseMapper purchaseMapper;
    private final ElectroItemMapper electroItemMapper;
    private final PositionTypeMapper positionTypeMapper;
    private final PurchaseTypeMapper purchaseTypeMapper;
    private final EmployeeDTOMapper employeeDTOMapper;

    private final ScvService scvService;


    @GetMapping
    public String mainPage() {
        return "index";
    }


    @GetMapping("/best-count")
    @ResponseBody
    public List<BestEmployeeDTO> getBestEmployees() {
        return filterService.getBestEmployees().stream().map(
                result -> {
                    Employee employee = (Employee) result[0];
                    Long totalSales = (Long) result[1];
                    Long itemsSold = (Long) result[2];
                    return employeeDTOMapper.toDTO(employee, totalSales, itemsSold);
                }
        ).sorted(Comparator.comparingLong(BestEmployeeDTO::getItemsSold).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping("/best-sales")
    @ResponseBody
    public List<BestEmployeeDTO> getBestEmployeesSales() {
        return filterService.findBestEmployeesSales().stream().map(
                result -> {
                    Employee employee = (Employee) result[0];
                    Long totalSales = (Long) result[1];
                    Long itemsSold = (Long) result[2];
                    return employeeDTOMapper.toDTO(employee, totalSales, itemsSold);
                }
        ).sorted(Comparator.comparingLong(BestEmployeeDTO::getTotalSales).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping("/best-junior-salesperson-smartwatches")
    @ResponseBody
    public List<BestEmployeeDTO> getBestJuniorSalespersonForSmartWatches() {

        List<Object[]> results = filterService.getBestJuniorSalespersonForSmartWatches();
        if (!results.isEmpty()) {
            Object[] result = results.get(0);
            Employee employee = (Employee) result[0];
            Long totalSales = (Long) result[1];
            Long itemsSold = (Long) result[2];
            return Collections
                    .singletonList(employeeDTOMapper.toDTO(employee, totalSales, itemsSold));
        }
        return null;
    }

    @GetMapping("/total-cash-payments")
    @ResponseBody
    public Long getTotalCashPayments() {
        return purchaseService.getTotalCashPayments();
    }

    @PostMapping("/upload-zip")
    public String uploadZipFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "File is empty");
            return "redirect:/api/v1/main";
        }

        try {
            scvService.processZipFile(file);
            redirectAttributes.addFlashAttribute("message", "File uploaded and processed successfully");
            return "redirect:/api/v1/main";
        } catch (IOException e) {
            log.error("Error processing file", e);
            redirectAttributes.addFlashAttribute("message", "Error processing file: " + e.getMessage());
            return "redirect:/api/v1/main";
        }
    }

    @GetMapping("/electrotovary")
    @ResponseBody
    public Map<String, Object> getElectroTovary(@RequestParam Integer page, @RequestParam Integer limit) {
        Page<ElectroItem> itemsPage = electroItemService.getAllElectroItemPageable(page - 1, limit);
        List<ElectroItemResponse> items = itemsPage.getContent().stream()
                .map(electroItemMapper::electroItemToResponse)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPages", itemsPage.getTotalPages());
        response.put("totalItems", itemsPage.getTotalElements());
        response.put("currentPage", page);
        return response;
    }

    @GetMapping("/sotrudniki")
    @ResponseBody
    public Map<String, Object> getSotrudniki(@RequestParam Integer page, @RequestParam Integer limit) {
        Page<Employee> itemsPage = employeeService.getAllEmployeePageable(page - 1, limit);
        List<EmployeeResponse> items = itemsPage.getContent().stream()
                .map(employeeMapper::employeeToResponse)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPages", itemsPage.getTotalPages());
        response.put("totalItems", itemsPage.getTotalElements());
        response.put("currentPage", page);
        return response;
    }

    @GetMapping("/pokupki")
    @ResponseBody
    public Map<String, Object> getPokupki(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam String sortOrder) {
        Page<Purchase> itemsPage = purchaseService.getAllPurchasePageable(page - 1, limit, sortOrder);
        List<PurchaseResponse> items = itemsPage.getContent().stream()
                .map(purchaseMapper::purchaseToResponse)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPages", itemsPage.getTotalPages());
        response.put("totalItems", itemsPage.getTotalElements());
        response.put("currentPage", page);
        return response;
    }

    @GetMapping("/references/position_type")
    @ResponseBody
    public Map<String, Object> getPositionType(@RequestParam Integer page, @RequestParam Integer limit) {
        Page<PositionType> itemsPage = positionTypeService.getAllPositionTypePageable(page - 1, limit);
        List<PositionTypeResponse> items = itemsPage.getContent().stream()
                .map(positionTypeMapper::positionTypeToResponse)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPages", itemsPage.getTotalPages());
        response.put("totalItems", itemsPage.getTotalElements());
        response.put("currentPage", page);
        return response;
    }

    @GetMapping("/references/purchase_type")
    @ResponseBody
    public Map<String, Object> getPurchaseType(@RequestParam Integer page, @RequestParam Integer limit) {
        Page<PurchaseType> itemsPage = purchaseTypeService.getAllPurchaseTypePageable(page - 1, limit);
        List<PurchaseTypeResponse> items = itemsPage.getContent().stream()
                .map(purchaseTypeMapper::purchaseTypeToResponse)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPages", itemsPage.getTotalPages());
        response.put("totalItems", itemsPage.getTotalElements());
        response.put("currentPage", page);
        return response;
    }

    @GetMapping("/references/electro_type")
    @ResponseBody
    public Map<String, Object> getElectroType(@RequestParam Integer page, @RequestParam Integer limit) {
        Page<ElectroType> itemsPage = electroTypeService.getAllElectroTypePageable(page - 1, limit);

        Map<String, Object> response = new HashMap<>();
        response.put("items", itemsPage.getContent());
        response.put("totalPages", itemsPage.getTotalPages());
        response.put("totalItems", itemsPage.getTotalElements());
        response.put("currentPage", page);
        return response;
    }

    @GetMapping("/references/shop")
    @ResponseBody
    public Map<String, Object> getShop(@RequestParam Integer page, @RequestParam Integer limit) {
        Page<Shop> itemsPage = shopService.getAllShopPageable(page - 1, limit);
        List<ShopResponse> items = itemsPage.getContent().stream()
                .map(shopMapper::shopToResponse)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPages", itemsPage.getTotalPages());
        response.put("totalItems", itemsPage.getTotalElements());
        response.put("currentPage", page);
        return response;
    }
}
