package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Entity
@Getter
@Setter
@Table(name = "books")
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Название книги не может быть пустым")
    private String title;

    @NotNull(message = "ISBN не может быть пустым")
    private String isbn;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorModel> authors;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherModel publisher;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreModel genre;
}
