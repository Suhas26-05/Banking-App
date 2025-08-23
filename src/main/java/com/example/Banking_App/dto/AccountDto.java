package com.example.Banking_App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private int id;
    private String accountHolderName;
    private double balance;
}
