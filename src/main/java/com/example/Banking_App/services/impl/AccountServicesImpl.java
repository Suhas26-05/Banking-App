package com.example.Banking_App.services.impl;

import com.example.Banking_App.dto.AccountDto;
import com.example.Banking_App.entity.Account;
import com.example.Banking_App.mapper.AccountMapper;
import com.example.Banking_App.repository.AccountRepository;
import com.example.Banking_App.services.AccountServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServicesImpl implements AccountServices {
    private AccountRepository accountRepository;


    public AccountServicesImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto CreateAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountId(Long Id) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Account Doesn't Exists "));
        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long Id, double amount) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Account Doesn't Exists "));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long Id, double amount) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Account Doesn't Exists "));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.maptoAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long Id) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Account Doesn't Exists "));
        accountRepository.deleteById(Id);
    }


}