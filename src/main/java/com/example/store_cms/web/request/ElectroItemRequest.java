package com.example.store_cms.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectroItemRequest {
    private String name;
    private Long price;
    private Integer count;
    private Boolean archive;
    private String description;
    private Long electroTypeId;
    private Long shopId;
    private Integer countLast;

}
