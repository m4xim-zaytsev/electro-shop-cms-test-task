package com.example.store_cms.model.key;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ElectroEmployeeId implements Serializable {
    private Long employeeId;
    private Long electroTypeId;
}
