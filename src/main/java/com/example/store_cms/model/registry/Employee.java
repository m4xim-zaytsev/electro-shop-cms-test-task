package com.example.store_cms.model.registry;

import com.example.store_cms.model.directory.ElectroEmployee;
import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.model.directory.Shop;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ElectroEmployee> electroEmployees;

    private Boolean gender;

    public String getFullName() {
        return firstName + " " + (patronymic != null ? patronymic + " " : "") + lastName;
    }
}
