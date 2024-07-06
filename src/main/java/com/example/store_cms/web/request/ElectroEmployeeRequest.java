package com.example.store_cms.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectroEmployeeRequest {
    private Long employeeId;
    private Long electroTypeId;
}
