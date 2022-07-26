package com.korolyovegor.onlineStoreBackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
@Data
public class Warehouse extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

}