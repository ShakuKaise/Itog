package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.Privilege;
import com.example.SpringAppV3.model.Role;
import com.example.SpringAppV3.repository.PrivilegeRepository;
import com.example.SpringAppV3.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository repository;
    private final PrivilegeRepository privilegeRepository;

    public RoleService(RoleRepository repository, PrivilegeRepository privilegeRepository) {
        this.repository = repository;
        this.privilegeRepository = privilegeRepository;
    }
    @PostConstruct
    public void init() {
        if (!repository.existsByName("SUPERUSER")) createSuperUserRole();
    }

    @Transactional

    public void createSuperUserRole() {
        Role role = new Role();
        role.setName("SUPERUSER");
        Set<Privilege> privileges = new HashSet<>(privilegeRepository.findAll());
        role.setPrivileges(privileges);
        repository.save(role);
    }
}
