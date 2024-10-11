package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "role_id")
    private Role role;

}