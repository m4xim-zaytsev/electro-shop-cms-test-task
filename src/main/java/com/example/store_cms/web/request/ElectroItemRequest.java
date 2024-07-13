package com.example.store_cms.web.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectroItemRequest {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 150)
    private String name;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be greater than 0")
    private Long price;

    @NotNull(message = "Count is mandatory")
    @PositiveOrZero(message = "Count must be greater than 0")
    private Integer count;

    @NotNull(message = "Archive status is mandatory")
    private Boolean archive;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "ElectroType ID is mandatory")
    private Long electroTypeId;
}