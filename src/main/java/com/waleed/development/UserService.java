package com.waleed.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public User addUser(User user) {
        user.getAccounts().forEach(account -> {
            account.setUser(user);
            account.setLastTransactionDate(LocalDateTime.now());
        });
        return userRepository.save(user);
    }

    @Cacheable("users")
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @CachePut(value = "users", key = "#user.username")
    public List<Account> getActiveAccounts(User user) {
        return accountRepository.findByUserAndStatusOrderByLastTransactionDateDesc(user, "active");
    }

    public Account addAccount(User user, Account account) {
        account.setUser(user);
        account.setLastTransactionDate(LocalDateTime.now());
        return accountRepository.save(account);
    }

    @CacheEvict(value = "users", key = "#user.username")
    public Account updateAccount(String accountNumber, Double lastTransactionAmount) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            account.setLastTransactionAmount(lastTransactionAmount);
            account.setLastTransactionDate(LocalDateTime.now());
            return accountRepository.save(account);
        }
        return null;
    }

//    @CacheEvict(value = "users", key = "#user.username")
//    public void deleteAccount(String accountNumber) {
//        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
//        accountOpt.ifPresent(accountRepository::delete);
//    }

    public void deleteAccount(String accountNumber) {
        accountRepository.deleteByAccountNumber(accountNumber);
    }
}