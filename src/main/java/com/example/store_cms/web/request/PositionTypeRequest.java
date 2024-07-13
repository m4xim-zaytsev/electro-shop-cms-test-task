package com.example.store_cms.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionTypeRequest {
    private Long id;
    @Size(max = 150)
    @NotBlank(message = "Name is mandatory")
    private String name;
}
