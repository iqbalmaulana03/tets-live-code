package com.iqbal.loginsystem.repository;

import com.iqbal.loginsystem.constant.EUser;
import com.iqbal.loginsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByUser(EUser role);
}
