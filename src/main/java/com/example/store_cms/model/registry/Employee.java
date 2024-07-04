package com.example.store_cms.model.registry;

import com.example.store_cms.model.directory.ElectroEmployee;
import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.model.directory.Shop;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100)
    private String patronymic;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private PositionType positionType;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<ElectroEmployee> electroEmployees;

    private Boolean gender;
}
