package com.korolyovegor.onlineStoreBackend.model.security;

import com.korolyovegor.onlineStoreBackend.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles", indexes = {
        @Index(name = "roles_name_key", columnList = "name", unique = true)
})
@Data
public class Role extends BaseEntity {

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "created", nullable = false)
    private Instant created;

    @Column(name = "updated", nullable = false)
    private Instant updated;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new LinkedHashSet<>();

}