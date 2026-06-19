package com.tutorial.bankaccount;


import java.math.BigDecimal;

public class BankAccount {
    String accountNumber;
    String accountHolder;
    BigDecimal balance;
    Integer overDraftLimit;

    public BankAccount(String accountNumber, String accountHolder, BigDecimal balance, Integer overDraftLimit) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.overDraftLimit = overDraftLimit;
    }

    public boolean deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("amount cannot be zero or negative");
            return false;
        }
        this.balance = this.balance.add(amount);
        return true;
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("amount cannot be zero or negative");
            return false;
        }
        if (amount.compareTo(this.getBalance()) > 0) {
            System.out.println("you dont have enough credit");
            return false;
        }
        this.balance = this.balance.subtract(amount);
        return true;
    }

    public boolean transfer(BankAccount to, BigDecimal amount) {
        if (to == null || amount == null) {
            System.out.println("the amount or destination card number cant be null");
            return false;
        }
        if (to == this) {
            System.out.println("the source and destination cant be same");
            return false;
        }
        if (amount.compareTo(this.getBalance()) > 0) {
            System.out.println("insufficient balance");
            return false;
        }
        this.balance = this.balance.subtract(amount);
        to.deposit(amount);
        return true;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
}

