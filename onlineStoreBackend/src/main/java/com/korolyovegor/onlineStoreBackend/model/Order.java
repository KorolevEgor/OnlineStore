package com.korolyovegor.onlineStoreBackend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {
    @OneToMany(mappedBy = "order")
    private List<Purchase> purchases;

}