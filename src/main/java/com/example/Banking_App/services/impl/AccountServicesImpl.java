package com.example.Banking_App.services.impl;

import com.example.Banking_App.dto.AccountDto;
import com.example.Banking_App.entity.Account;
import com.example.Banking_App.entity.AccountType;
import com.example.Banking_App.entity.Transaction;
import com.example.Banking_App.mapper.AccountMapper;
import com.example.Banking_App.repository.AccountRepository;
import com.example.Banking_App.repository.TransactionRepository;
import com.example.Banking_App.services.AccountServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServicesImpl implements AccountServices {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public AccountServicesImpl(AccountRepository accountRepository,
                               TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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
        account.setBalance(account.getBalance() + amount);

        transactionRepository.save(new Transaction(null, "DEPOSIT", amount, LocalDateTime.now(), account));

        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long Id, double amount) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Account Doesn't Exists "));

        double totalDeduction = amount;
        if (account.getAccountType() == AccountType.CHECKING) {
            totalDeduction += 10; // Fee for checking accounts
        }

        if(account.getBalance() < totalDeduction){
            throw new RuntimeException("Insufficient Amount");
        }

        account.setBalance(account.getBalance() - totalDeduction);

        transactionRepository.save(new Transaction(null, "WITHDRAW", amount, LocalDateTime.now(), account));
        if (totalDeduction > amount) {
            transactionRepository.save(new Transaction(null, "FEE", totalDeduction - amount, LocalDateTime.now(), account));
        }

        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::maptoAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long Id) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Account Doesn't Exists "));

        // Delete all related transactions first
        List<Transaction> transactions = transactionRepository.findByAccountId((long) account.getId());
        transactionRepository.deleteAll(transactions);

        accountRepository.deleteById(Id);
    }


    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public void applyMonthlyInterest() {
        List<Account> accounts = accountRepository.findAll();
        for (Account acc : accounts) {
            if (acc.getAccountType() == AccountType.SAVINGS) {
                double interest = acc.getBalance() * 0.03;
                acc.setBalance(acc.getBalance() + interest);
                transactionRepository.save(new Transaction(null, "INTEREST", interest, LocalDateTime.now(), acc));
                accountRepository.save(acc);
            }
        }
    }
}
