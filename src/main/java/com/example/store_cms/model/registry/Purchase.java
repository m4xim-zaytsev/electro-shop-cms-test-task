package com.example.store_cms.model.registry;

import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.model.directory.Shop;
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
    @JoinColumn(name = "electro_id",referencedColumnName = "id")
    private ElectroItem electroItem;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private PurchaseType purchaseType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;
}
