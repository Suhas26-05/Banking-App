package com.example.Banking_App.services;

import com.example.Banking_App.dto.AccountDto;

import java.util.List;

public interface AccountServices {
    AccountDto CreateAccount(AccountDto accountDto);

    AccountDto getAccountId(Long Id);

    AccountDto deposit(Long Id, double amount);

    AccountDto withdraw(Long Id, double amount);

    List<AccountDto> getAllAccount();

    void deleteAccount(Long Id);
}
