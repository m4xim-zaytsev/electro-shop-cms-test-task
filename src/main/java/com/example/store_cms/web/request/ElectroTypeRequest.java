package com.example.store_cms.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectroTypeRequest {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 150)
    private String name;
}