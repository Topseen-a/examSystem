package com.examsystem.data.repository;

import com.examsystem.data.entity.Role;
import com.examsystem.data.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);
}
