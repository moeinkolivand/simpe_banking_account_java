package com.tutorial.bankaccount;


import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount {
    private final String accountNumber;
    private final String accountHolder;
    BigDecimal balance;
    Integer overDraftLimit;

    public BankAccount(String accountNumber, String accountHolder, BigDecimal balance, Integer overDraftLimit) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.overDraftLimit = overDraftLimit;
    }

    public boolean deposit(BigDecimal amount) throws InvalidAmountException {
        this.validateAmount(amount);
        this.balance = this.balance.add(amount);
        return true;
    }

    public boolean withdraw(BigDecimal amount) throws InvalidAmountException {
        this.validateAmount(amount);
        if (amount.compareTo(this.getBalance()) > 0) {
            System.out.println("you dont have enough credit");
            return false;
        }
        this.balance = this.balance.subtract(amount);
        return true;
    }

    public boolean transfer(BankAccount to, BigDecimal amount) throws InvalidAmountException {
        this.validateAmount(amount);
        if (Objects.equals(this, to)) {
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

    private void validateAmount(BigDecimal amount) throws InvalidAmountException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("The Amount Must Be Positive, was: " + amount);
        }
    }
}

