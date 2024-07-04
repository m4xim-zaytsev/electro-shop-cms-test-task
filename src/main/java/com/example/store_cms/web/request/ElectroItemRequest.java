package com.example.store_cms.web.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectroItemRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be greater than 0")
    private Long price;

    @NotNull(message = "Count is mandatory")
    @Positive(message = "Count must be greater than 0")
    private Integer count;

    @NotNull(message = "Archive status is mandatory")
    private Boolean archive;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "ElectroType ID is mandatory")
    private Long electroTypeId;

    @NotNull(message = "Shop ID is mandatory")
    private Long shopId;

    @NotNull(message = "CountLast is mandatory")
    @Positive(message = "CountLast must be greater than 0")
    private Integer countLast;
}