package com.example.store_cms.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {
    private Long id;

    @NotNull(message = "ElectroItem ID is mandatory")
    private Long electroItemId;
    @NotNull(message = "Employee ID is mandatory")
    private Long employeeId;
    @NotNull(message = "Shop ID is mandatory")
    private Long shopId;
    @NotNull(message = "PurchaseType ID is mandatory")
    private Long purchaseTypeId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Purchase date is mandatory")
    private Date purchaseDate;
}
