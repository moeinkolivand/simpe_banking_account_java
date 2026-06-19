package com.tutorial;

import com.tutorial.bankaccount.BankAccount;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        BankAccount bankAccountOne = new BankAccount(
                "123",
                "moein",
                new BigDecimal("1000000"),
                1000
        );
        BankAccount bankAccountTwo = new BankAccount(
                "321",
                "ahmad",
                new BigDecimal("1000000"),
                null
        );
        bankAccountOne.deposit(new BigDecimal("500000"));
        System.out.println(bankAccountOne.getBalance());
        bankAccountOne.withdraw(new BigDecimal("500"));
        System.out.println(bankAccountOne.getBalance());
        bankAccountOne.transfer(bankAccountTwo, new BigDecimal("50000000000000"));
        System.out.println("Done");
    }
}
