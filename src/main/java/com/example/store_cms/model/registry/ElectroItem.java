package com.example.store_cms.model.registry;

import com.example.store_cms.model.directory.ElectroShop;
import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.model.directory.Shop;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ElectroItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 150, nullable = false)
        private String name;

        private Long price;
        private Integer count;
        private Boolean archive;
        private String description;

        @ManyToOne
        @JoinColumn(name = "etype_id", referencedColumnName = "id")
        private ElectroType electroType;

        @OneToMany(mappedBy = "electroItem")
        @JsonManagedReference
        private List<ElectroShop> electroShops;
}