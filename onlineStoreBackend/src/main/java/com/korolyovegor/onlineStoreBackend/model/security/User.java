package com.korolyovegor.onlineStoreBackend.model.security;

import com.korolyovegor.onlineStoreBackend.model.BaseEntity;
import com.korolyovegor.onlineStoreBackend.model.Purchase;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private UserStatusType status;

    @OneToMany(mappedBy = "user")
    private Set<Purchase> purchases = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles = new LinkedHashSet<>();

}