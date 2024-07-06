package com.example.store_cms.model.registry;

import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.model.directory.Shop;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "electro_id")
    private ElectroItem electroItem;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PurchaseType purchaseType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;
}
