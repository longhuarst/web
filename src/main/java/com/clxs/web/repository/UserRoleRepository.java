package com.clxs.web.repository;

import com.clxs.web.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,String> {
    List<UserRole> findByUserId(Integer userId);
}
