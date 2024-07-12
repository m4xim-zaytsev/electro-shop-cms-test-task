package com.example.store_cms.model.directory;

import com.example.store_cms.model.key.ElectroEmployeeId;
import com.example.store_cms.model.registry.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "electro_employee")
public class ElectroEmployee {
    @EmbeddedId
    private ElectroEmployeeId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    @ManyToOne
    @MapsId("electroTypeId")
    @JoinColumn(name = "electro_type_id")
    private ElectroType electroType;
}
