package com.example.Banking_App.controller;

import com.example.Banking_App.dto.AccountDto;
import com.example.Banking_App.services.AccountServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountServices accountServices;

    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }

    // Add Account Rest API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountServices.CreateAccount(accountDto), HttpStatus.CREATED);
    }

    // Get Account Rest API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        AccountDto accountDto = accountServices.getAccountId(id);
        return ResponseEntity.ok(accountDto);
    }

    // Get Deposit Rest API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request){
        Double amount = request.get("Amount");
        AccountDto accountDto = accountServices.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Withdraw Rest API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request){
        Double amount = request.get("Amount");
        AccountDto accountDto = accountServices.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Get All Account Rest API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getALlAccounts(){
        List<AccountDto> accounts = accountServices.getAllAccount();
        return ResponseEntity.ok(accounts);
    }

    // Delete Account Rest API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountServices.deleteAccount(id);
        return ResponseEntity.ok("Account is Deleted Successfully");
    }
}
