package com.example.store_cms.model.directory;


import com.example.store_cms.model.registry.Purchase;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


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
}