package com.todo.reposotory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
