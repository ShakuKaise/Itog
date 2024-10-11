package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Data
@Entity
@Table(name = "authors")
public class AuthorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Имя автора не может быть пустым")
    private String name;

    @NotNull(message = "Фамилия автора не может быть пустой")
    private String surname;

    @ManyToMany(mappedBy = "authors")
    private Set<BookModel> books;
}
