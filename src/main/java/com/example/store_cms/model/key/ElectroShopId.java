package com.example.store_cms.model.key;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ElectroShopId implements Serializable {
    private Long shopId;
    private Long electroItemId;

}