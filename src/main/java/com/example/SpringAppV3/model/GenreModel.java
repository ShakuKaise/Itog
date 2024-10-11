package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "genres")
public class GenreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Название жанра не может быть пустым")
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<BookModel> books;
}
