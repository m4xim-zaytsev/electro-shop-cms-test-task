package com.example.store_cms.model.directory;

import com.example.store_cms.model.registry.Purchase;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @OneToMany(mappedBy = "purchaseType", cascade = CascadeType.ALL)
    private List<Purchase> purchases;
}