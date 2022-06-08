package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Role;
import com.example.domain.User;

public interface RoleRepository  extends JpaRepository<Role, Long>{

	  Role findByName(String name);
}
