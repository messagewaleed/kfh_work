package com.waleed.development;

import java.time.LocalDateTime;

public class AccountDTO {

    private Long accountId;
    private String accountNumber;
    private Double balance;
    private Double lastTransactionAmount;
    private LocalDateTime lastTransactionDate;
    private String status;

    // Getters and Setters

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getLastTransactionAmount() {
        return lastTransactionAmount;
    }

    public void setLastTransactionAmount(Double lastTransactionAmount) {
        this.lastTransactionAmount = lastTransactionAmount;
    }

    public LocalDateTime getLastTransactionDate() {
        return lastTransactionDate;
    }

    public void setLastTransactionDate(LocalDateTime lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
