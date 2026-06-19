package com.tutorial.bankaccount;


import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount {
    private final String accountNumber;
    private final String accountHolder;
    private BigDecimal balance;
    private Integer overDraftLimit;

    public BankAccount(String accountNumber, String accountHolder, BigDecimal balance, Integer overDraftLimit) {
        Objects.requireNonNull(accountNumber, "the account number can`t be null");
        Objects.requireNonNull(accountHolder, "the account holder can`t be null");
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = Objects.requireNonNullElseGet(balance, () -> BigDecimal.ZERO);
        this.overDraftLimit = overDraftLimit;
    }

    public BigDecimal deposit(BigDecimal amount) {
        this.validateAmount(amount);
        this.balance = this.getBalance().add(amount);
        return this.getBalance();
    }

    public BigDecimal withdraw(BigDecimal amount) {
        this.validateAmount(amount);
        this.validateBalance(amount, this.getBalance());
        this.balance = this.balance.subtract(amount);
        return this.getBalance();
    }

    public BigDecimal transfer(BankAccount to, BigDecimal amount) throws InsufficientFundsException {
        this.validateAmount(amount);
        if (Objects.equals(this, to)) {
            throw new SelfTransferException("You Cant Transfer To Your Self");
        }
        this.validateBalance(amount, this.getBalance());
        try {
            this.balance = this.balance.subtract(amount);
            to.deposit(amount);
        } catch (Exception e) {
            this.balance = this.balance.add(amount);
            throw new RuntimeException(e);
        }
        return this.getBalance();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    private void validateAmount(BigDecimal amount) throws InvalidAmountException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("The Amount Must Be Positive, was: " + amount);
        }
    }

    private void validateBalance(BigDecimal amount, BigDecimal balance) throws InsufficientFundsException {
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsException("Insufficient Balance");
        }
    }
}

