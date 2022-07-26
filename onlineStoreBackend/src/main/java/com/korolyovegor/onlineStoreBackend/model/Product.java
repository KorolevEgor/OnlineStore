package com.korolyovegor.onlineStoreBackend.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "product")
    private Set<Purchase> purchases = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new LinkedHashSet<>();

}