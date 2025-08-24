package com.example.Banking_App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private int id;
    private String accountHolderName;
    private double balance;
    private String accountType;  // ✅ Added
}
