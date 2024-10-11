package com.example.SpringAppV3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "returns")
public class ReturnModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Дата возврата не может быть пустой")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @NotNull(message = "Причина возврата не может быть пустой")
    private String reason;
}
