package com.example.SpringAppV3.service;


import com.example.SpringAppV3.model.Privilege;
import com.example.SpringAppV3.model.Role;
import com.example.SpringAppV3.model.User;
import com.example.SpringAppV3.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.example.SpringAppV3.model.User user = userRepository.findByEmail(email);


        Set<GrantedAuthority> authorities = getAuthorities(user.getRole());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
    public UserDetails loadUserById(long userId) throws UsernameNotFoundException {
        com.example.SpringAppV3.model.User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );
    }



    // Получение привилегий пользователя на основе его роли
    private Set<GrantedAuthority> getAuthorities(Role role) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Добавляем саму роль как GrantedAuthority
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

        // Добавляем привилегии, если они существуют
        for (Privilege privilege : role.getPrivileges()) {
            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
        }

        return authorities;
    }
}


