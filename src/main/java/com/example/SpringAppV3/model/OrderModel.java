package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Дата создания не может быть пустой")
    private LocalDateTime creationDate;

    @NotNull(message = "Статус заказа не может быть пустым")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private ReaderModel reader;

    @ManyToMany
    @JoinTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<BookModel> books;

    public enum OrderStatus {
        НОВЫЙ, ОБРАБОТКА, ОТПРАВЛЕН, ДОСТАВЛЕН, ОТМЕНЕН
    }
}
