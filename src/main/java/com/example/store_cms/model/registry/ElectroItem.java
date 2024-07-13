package com.example.store_cms.model.registry;

import com.example.store_cms.model.directory.ElectroShop;
import com.example.store_cms.model.directory.ElectroType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

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
        @NotBlank(message = "Наименование не может быть пустым")
        @Size(max = 150)
        private String name;

        @Positive(message = "Цена должна быть положительной")
        private Long price;

        @PositiveOrZero(message = "Количество должно быть положительным или нулевым")
        private Integer count;

        private Boolean archive;

        @Column(length = 1000) // Example length for description
        private String description;

        @ManyToOne
        @JoinColumn(name = "etype_id")
        private ElectroType electroType;

        @OneToMany(mappedBy = "electroItem", cascade = CascadeType.ALL)
        @JsonManagedReference
        private List<Purchase> purchases;

        @OneToMany(mappedBy = "electroItem", cascade = CascadeType.ALL)
        @JsonManagedReference
        private List<ElectroShop> electroShops;
}