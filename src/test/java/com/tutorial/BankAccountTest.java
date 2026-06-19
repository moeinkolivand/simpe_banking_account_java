package com.tutorial;

import com.tutorial.bankaccount.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bank Account Testing Behavior")
public class BankAccountTest {

    private BankAccount bankAccountOne;
    private BankAccount bankAccountTwo;
    private final BigDecimal bankAccountOneAmount = new BigDecimal("1000000");
    private final BigDecimal bankAccountTwoAmount = new BigDecimal("5000000");


    @BeforeEach
    void setup() {
        bankAccountOne = new BankAccount(
                "1",
                "alex",
                bankAccountOneAmount,
                null
        );
        bankAccountTwo = new BankAccount(
                "2",
                "sara",
                bankAccountTwoAmount,
                null
        );

    }

    private static Stream<Arguments> invalidAmountProvider() {
        return Stream.of(
                Arguments.of(new BigDecimal("-100000")),
                Arguments.of(new BigDecimal("0")),
                Arguments.of((Object) null)
        );
    }


    @Test
    @DisplayName("Perform A Simple Deposit")
    public void bankAccountDeposit() {
        BigDecimal amount = new BigDecimal("100000");
        BigDecimal result = bankAccountOne.getBalance().add(amount);
        bankAccountOne.deposit(amount);
        assertEquals(result, bankAccountOne.getBalance());
    }

    @ParameterizedTest
    @MethodSource("invalidAmountProvider")
    @DisplayName("Perform A Negative - Zero - Null Deposit")
    public void bankAccountNegativeDeposit(BigDecimal amount) {
        BigDecimal result = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.deposit(amount));
        assertEquals(result, bankAccountOne.getBalance());
    }


    @Test
    @DisplayName("Perform A Withdraw")
    public void bankAccountWithdraw() {
        BigDecimal amount = new BigDecimal("50000");
        BigDecimal result = bankAccountOne.getBalance().subtract(amount);
        bankAccountOne.withdraw(amount);
        assertEquals(result, bankAccountOne.getBalance());
    }

    @ParameterizedTest
    @MethodSource("invalidAmountProvider")
    @DisplayName("Perform A Negative Withdraw")
    public void bankAccountNegativeWithdraw(BigDecimal amount) {
        BigDecimal result = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.withdraw(amount));
        assertEquals(result, bankAccountOne.getBalance());
    }

    @Test
    @DisplayName("Perform A WidthDraw With Insufficient Balance")
    public void bankAccountWithdrawWithInsufficientBalance() {
        BigDecimal amount = new BigDecimal("1000000000000000000");
        assertThrows(InsufficientFundsException.class, () -> bankAccountOne.withdraw(amount));
    }

    @Test
    @DisplayName("Perform A Transfer")
    public void bankAccountTransfer() {
        BigDecimal amount = new BigDecimal("5000");
        BigDecimal resultBankAccountOne = bankAccountOne.getBalance().subtract(amount);
        BigDecimal resultBankAccountTwo = bankAccountTwo.getBalance().add(amount);
        bankAccountOne.transfer(bankAccountTwo, amount);
        assertEquals(resultBankAccountOne, bankAccountOne.getBalance());
        assertEquals(resultBankAccountTwo, bankAccountTwo.getBalance());
    }

    @Test
    @DisplayName("Perform A Transfer")
    public void bankAccountTransferWithInsufficientBalance() {
        BigDecimal amount = new BigDecimal("5000000000000000");
        assertThrows(InsufficientFundsException.class, () -> bankAccountOne.transfer(bankAccountTwo, amount));
    }

    @Test
    @DisplayName("Perform A Self Transfer")
    public void bankAccountSelfTransfer() {
        BigDecimal amount = new BigDecimal("5000");
        BigDecimal resultBankAccountOne = bankAccountOne.getBalance();
        assertThrows(SelfTransferException.class, () -> bankAccountOne.transfer(bankAccountOne, amount));
        assertEquals(resultBankAccountOne, bankAccountOne.getBalance());
    }


    @Test
    @DisplayName("Perform A Transfer To null")
    public void bankAccountTransferToNull() {
        BigDecimal amount = new BigDecimal("5000");
        BigDecimal resultBankAccountOne = bankAccountOne.getBalance();
        assertThrows(RuntimeException.class, () -> bankAccountOne.transfer(null, amount));
        assertEquals(resultBankAccountOne, bankAccountOne.getBalance());
    }


    @ParameterizedTest
    @MethodSource("invalidAmountProvider")
    @DisplayName("Perform A Zero Transfer")
    public void bankAccountZeroTransfer(BigDecimal amount) {
        BigDecimal resultBankAccountOne = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.transfer(bankAccountTwo, amount));
        assertEquals(resultBankAccountOne, bankAccountOne.getBalance());
    }
}
