package com.studentportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentportal.bean.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRole(String role);
}
