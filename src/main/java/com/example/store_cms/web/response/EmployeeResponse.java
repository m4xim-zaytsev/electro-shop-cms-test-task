package com.example.store_cms.web.response;

import com.example.store_cms.model.directory.PositionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse{
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private Boolean gender;
    private ShopResponse shopResponse;
    private PositionTypeResponse positionTypeResponse;
}
