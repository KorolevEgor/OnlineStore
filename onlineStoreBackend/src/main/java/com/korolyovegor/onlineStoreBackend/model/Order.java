package com.korolyovegor.onlineStoreBackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

}