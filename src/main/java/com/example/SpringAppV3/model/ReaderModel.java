package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "readers")
public class ReaderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Имя читателя не может быть пустым")
    private String firstName;

    @NotNull(message = "Фамилия читателя не может быть пустой")
    private String lastName;

    @OneToMany(mappedBy = "reader")
    private Set<OrderModel> orders;
}
