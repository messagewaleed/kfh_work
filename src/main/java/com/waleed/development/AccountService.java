package com.waleed.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account addAccount(String username, Account account) {
        var user = userRepository.findByUsername(username);
//        account.setUser(user);
        return accountRepository.save(account);
    }

    public List<Account> listActiveAccounts(String username) {
        return null;
//                accountRepository.findByUserAndStatusOrderByLastTransactionDateDesc(username, "active");
    }

//    public void deleteAccount(String accountNumber) {
//        accountRepository.deleteById();
//    }

    public Account updateAccount(String accountNumber, Double lastTransactionAmount) {
        var account = accountRepository.findById(Long.parseLong(accountNumber)).orElseThrow();
        account.setLastTransactionAmount(lastTransactionAmount);
        account.setLastTransactionDate(LocalDateTime.now());
        return accountRepository.save(account);
    }
}
