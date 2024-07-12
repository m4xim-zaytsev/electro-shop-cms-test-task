package com.example.store_cms.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    private String patronymic;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Correct format for HTML <input type="date">
    @NotNull(message = "Birth date is mandatory")
    private Date birthDate;

    @NotNull(message = "Gender is mandatory")
    private Boolean gender;

    @NotNull(message = "Shop ID is mandatory")
    private Long shopId;

    @NotNull(message = "Position ID is mandatory")
    private Long positionId;
}