package com.example.store_cms.web.controller.rest;

import com.example.store_cms.mapper.*;
import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.model.registry.ElectroItem;
import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.model.registry.Purchase;
import com.example.store_cms.service.*;
import com.example.store_cms.web.request.*;
import com.example.store_cms.web.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class AllEntitiesControllerUseToPostmanOrTest {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    private final PositionTypeService positionTypeService;
    private final PositionTypeMapper positionTypeMapper;

    private final ElectroTypeService electroTypeService;
    private final ElectroTypeMapper electroTypeMapper;

    private final ElectroItemService electroItemService;
    private final ElectroItemMapper electroItemMapper;

    private final PurchaseTypeService purchaseTypeService;
    private final PurchaseTypeMapper purchaseTypeMapper;

    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;


    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request){
        Employee employee = employeeService.create(employeeMapper.requestToEmployee(request),
                request.getPositionId(),request.getShopId(),request.getElectroTypeId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeMapper.
                        employeeToResponse(employee));
    }

    @PostMapping("/shop")
    public ResponseEntity<ShopResponse> createShop(@RequestBody ShopRequest request){
        Shop newShop = shopService.create(shopMapper.requestToShop(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(shopMapper.
                        shopToResponse(newShop));
    }

    @PostMapping("/position")
    public ResponseEntity<PositionTypeResponse> createRole(@RequestBody PositionTypeRequest request){
        PositionType type = positionTypeService.create(positionTypeMapper.requestToPositionType(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(positionTypeMapper.
                        positionTypeToResponse(type));
    }

    @PostMapping("/electrotype")
    public ResponseEntity<ElectroTypeResponse> createElectroType(@RequestBody ElectroTypeRequest request) {
        ElectroType electroType = electroTypeService.create(electroTypeMapper.requestToElectroType(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(electroTypeMapper.electroTypeToResponse(electroType));
    }

    @PostMapping("/electroitem")
    public ResponseEntity<ElectroItemResponse> createElectroItem(@RequestBody ElectroItemRequest request) {
        ElectroItem electroItem = electroItemService.create(electroItemMapper.requestToElectroItem(request),request.getElectroTypeId(),
                request.getShopId(),request.getCountLast());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(electroItemMapper.electroItemToResponse(electroItem));
    }

    @PostMapping("/purchasetype")
    public ResponseEntity<PurchaseTypeResponse> createPurchaseType(@RequestBody PurchaseTypeRequest request) {
        PurchaseType purchaseType = purchaseTypeService.create(purchaseTypeMapper.requestToPurchaseType(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(purchaseTypeMapper.purchaseTypeToResponse(purchaseType));
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> createPurchase(@RequestBody PurchaseRequest request) {
        Purchase purchase = purchaseService.create(purchaseMapper.requestToPurchase(request), request.getElectroItemId(),
                request.getEmployeeId(), request.getPurchaseTypeId(), request.getShopId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(purchaseMapper.purchaseToResponse(purchase));
    }
}
