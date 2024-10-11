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
@Table(name = "publishers")
public class PublisherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Название издательства не может быть пустым")
    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<BookModel> books;
}
