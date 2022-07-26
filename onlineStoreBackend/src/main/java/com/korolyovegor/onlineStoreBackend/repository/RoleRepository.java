package com.korolyovegor.onlineStoreBackend.repository;

import com.korolyovegor.onlineStoreBackend.model.security.Role;
import com.korolyovegor.onlineStoreBackend.model.security.RoleNameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleNameType name);
}