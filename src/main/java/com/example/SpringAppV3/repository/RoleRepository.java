package com.example.SpringAppV3.repository;

import com.example.SpringAppV3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
 boolean existsByName(String name);

 Role findByName(String name);
}
