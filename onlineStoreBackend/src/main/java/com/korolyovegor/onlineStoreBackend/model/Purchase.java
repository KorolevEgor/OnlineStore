package com.korolyovegor.onlineStoreBackend.model;

import com.korolyovegor.onlineStoreBackend.model.security.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
@Data
public class Purchase extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}