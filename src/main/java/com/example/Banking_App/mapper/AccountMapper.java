package com.example.Banking_App.mapper;

import com.example.Banking_App.dto.AccountDto;
import com.example.Banking_App.entity.Account;
import com.example.Banking_App.entity.AccountType;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        return new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                AccountType.valueOf(accountDto.getAccountType().toUpperCase())
        );
    }

    public static AccountDto maptoAccountDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getAccountType().name()
        );
    }
}
