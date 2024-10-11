package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.User;
import com.example.SpringAppV3.repository.RoleRepository;
import com.example.SpringAppV3.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordService passwordService;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, PasswordService passwordService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordService = passwordService;
    }
    @PostConstruct
    public void init() {
        if (!userRepository.existsByEmail("ShakuX@gmail.com")) {
            User user = new User();
            user.setPassword(passwordEncoder.encode("ShakuX"));
            user.setFirstName("Misha");
            user.setLastName("Sav");
            user.setEmail("ShakuX@gmail.com");
            user.setRole(roleRepository.findByName("SUPERUSER"));
            userRepository.save(user);
        }
    }
    public User registerUser(String username, String password, String firstName, String lastName) {
        if (userRepository.findByEmail(username) != null) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        User newUser = new User();
        newUser.setEmail(username);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(passwordService.hashPassword(password));
        newUser.setRole(roleRepository.findByName("SUPERUSER"));

        return userRepository.save(newUser);
    }
}