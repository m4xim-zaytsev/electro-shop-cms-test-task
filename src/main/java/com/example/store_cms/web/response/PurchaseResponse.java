package com.example.store_cms.web.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {
    private Long id;
    private ElectroItemResponse electroItemResponse;
    private EmployeeResponse employeeResponse;
    private ShopResponse shopResponse;
    private PurchaseTypeResponse purchaseTypeResponse;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date purchaseDate;
}