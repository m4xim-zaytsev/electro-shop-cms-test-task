package com.example.store_cms.model.directory;

import com.example.store_cms.model.key.ElectroShopId;
import com.example.store_cms.model.registry.ElectroItem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ElectroShop {
    @EmbeddedId
    private ElectroShopId id;

    @ManyToOne
    @MapsId("shopId")
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    @ManyToOne
    @MapsId("electroItemId")
    @JoinColumn(name = "electro_item_id")
    @JsonBackReference
    private ElectroItem electroItem;

    private Integer count;
}