package com.example.store_cms.model.directory;

import com.example.store_cms.model.registry.ElectroItem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ElectroShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "electro_item_id")
    @JsonBackReference
    private ElectroItem electroItem;

    private Integer count;
}