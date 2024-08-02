package com.waleed.development;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserAndStatusOrderByLastTransactionDateDesc(User user, String status);
    Optional<Account> findByAccountNumber(String accountNumber);

    void deleteByAccountNumber(String accountNumber);
}
