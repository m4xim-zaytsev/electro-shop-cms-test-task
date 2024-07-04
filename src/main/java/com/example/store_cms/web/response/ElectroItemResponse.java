package com.example.store_cms.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectroItemResponse {
    private Long id;
    private String name;
    private Long price;
    private Integer count;
    private Boolean archive;
    private String description;
    private ElectroTypeResponse electroTypeResponse;
}
