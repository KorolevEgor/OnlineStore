package com.korolyovegor.onlineStoreBackend.model;

import com.korolyovegor.onlineStoreBackend.model.security.User;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "purchases")
@Data
public class Purchase extends BaseEntity {

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "purchase")
    private Set<Order> orders = new LinkedHashSet<>();

}