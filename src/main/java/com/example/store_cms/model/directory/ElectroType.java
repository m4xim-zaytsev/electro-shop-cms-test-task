package com.example.store_cms.model.directory;

import com.example.store_cms.model.registry.ElectroItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ElectroType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @OneToMany(mappedBy = "electroType",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ElectroItem> electroItems;

    @OneToMany(mappedBy = "electroType",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ElectroEmployee> electroEmployees;
}