package com.example.store_cms.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectroTypeRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
}