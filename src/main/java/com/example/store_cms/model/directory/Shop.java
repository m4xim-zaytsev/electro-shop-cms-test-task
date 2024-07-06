package com.example.store_cms.model.directory;

import com.example.store_cms.model.registry.Employee;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    private String address;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ElectroShop> electroShops;

    @OneToMany(mappedBy = "shop",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employees;
}