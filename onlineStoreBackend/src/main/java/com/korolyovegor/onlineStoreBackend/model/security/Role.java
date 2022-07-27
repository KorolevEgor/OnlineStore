package com.korolyovegor.onlineStoreBackend.model.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.korolyovegor.onlineStoreBackend.model.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles", indexes = {
        @Index(name = "roles_name_key", columnList = "name", unique = true)
})
@Data
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, length = 30)
    private RoleNameType name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private RoleStatusType status;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String getAuthority() {
        return name.toString();
    }

    public Role(RoleNameType name, RoleStatusType status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + super.getId() +
                ", name=" + name +
                ", status=" + status +
                '}';
    }
}