package com.korolyovegor.onlineStoreBackend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity {

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "name", nullable = false, length = 70)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new LinkedHashSet<>();

}