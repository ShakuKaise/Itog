package com.example.SpringAppV3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
