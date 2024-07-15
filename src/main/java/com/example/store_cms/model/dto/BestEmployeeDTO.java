package com.example.store_cms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BestEmployeeDTO {
    private Long id;
    private String fullName;
    private Long itemsSold;
    private Long totalSales;
    private String position;
}
