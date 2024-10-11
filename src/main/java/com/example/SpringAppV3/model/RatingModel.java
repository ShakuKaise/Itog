package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ratings")
public class RatingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Рейтинг должен быть не менее 1")
    @Max(value = 5, message = "Рейтинг не может превышать 5")
    private int score;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private ReaderModel reader;
}
