package com.example.store_cms.model.directory;

import com.example.store_cms.model.registry.Employee;
import com.example.store_cms.model.registry.Purchase;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PositionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @OneToMany(mappedBy = "positionType", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employees;
}