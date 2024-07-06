package com.example.store_cms.model.dto;

import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.model.directory.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWithSalesDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Date birthDate;
    private PositionType positionType;
    private Shop shop;
    private Boolean gender;
    private Long totalProductsSold;
}