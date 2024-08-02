package com.waleed.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public Account addAccount(@RequestParam String username, @RequestBody Account account) {
        return accountService.addAccount(username, account);
    }

    @GetMapping("/list")
    public List<Account> listActiveAccounts(@RequestParam String username) {
        return accountService.listActiveAccounts(username);
    }

//    @DeleteMapping("/delete")
//    public void deleteAccount(@RequestParam String accountNumber) {
//        accountService.deleteAccount(accountNumber);
//    }

    @PutMapping("/update")
    public Account updateAccount(@RequestParam String accountNumber, @RequestParam Double lastTransactionAmount) {
        return accountService.updateAccount(accountNumber, lastTransactionAmount);
    }
}
