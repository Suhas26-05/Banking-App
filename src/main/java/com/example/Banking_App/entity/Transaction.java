package com.example.Banking_App.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;   // DEPOSIT, WITHDRAW, FEE, INTEREST
    private double amount;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
