package com.example.Banking_App.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "Account_Holder_Name")
    private String AccountHolderName;

    private double Balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "Account_Type")
    private AccountType accountType;   // ✅ Savings / Checking
}
