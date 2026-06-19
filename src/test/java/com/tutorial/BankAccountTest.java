package com.tutorial;

import com.tutorial.bankaccount.BankAccount;
import com.tutorial.bankaccount.InvalidAmountException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

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

    @AfterEach
    void tearDown() {
        bankAccountOne = null;
        bankAccountTwo = null;
    }

    @Test
    @DisplayName("Perform A Simple Deposit")
    public void bankAccountDeposit() {
        BigDecimal amount = new BigDecimal("100000");
        BigDecimal result = bankAccountOne.getBalance().add(amount);
        bankAccountOne.deposit(amount);
        assertEquals(result, bankAccountOne.getBalance());
    }

    @Test
    @DisplayName("Perform A Negative Deposit")
    public void bankAccountNegativeDeposit() {
        BigDecimal amount = new BigDecimal("-100000");
        BigDecimal result = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.deposit(amount));
        assertEquals(result, bankAccountOne.getBalance());
    }

    @Test
    @DisplayName("Perform A Null Deposit")
    public void bankAccountNullDeposit() {
        BigDecimal amount = null;
        BigDecimal result = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.deposit(amount));
        assertEquals(result, bankAccountOne.getBalance());
    }

    @Test
    @DisplayName("Perform A Zero Deposit")
    public void bankAccountZeroDeposit() {
        BigDecimal amount = new BigDecimal("0");
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

    @Test
    @DisplayName("Perform A Negative Withdraw")
    public void bankAccountNegativeWithdraw() {
        BigDecimal amount = new BigDecimal("-50000");
        BigDecimal result = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.withdraw(amount));
        assertEquals(result, bankAccountOne.getBalance());
    }


    @Test
    @DisplayName("Perform A Zero Withdraw")
    public void bankAccountZeroWithdraw() {
        BigDecimal amount = new BigDecimal("0");
        BigDecimal result = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.withdraw(amount));
        assertEquals(result, bankAccountOne.getBalance());
    }


    @Test
    @DisplayName("Perform A Null Withdraw")
    public void bankAccountNullWithdraw() {
        BigDecimal amount = null;
        BigDecimal result = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.withdraw(amount));
        assertEquals(result, bankAccountOne.getBalance());
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
    @DisplayName("Perform A Self Transfer")
    public void bankAccountSelfTransfer() {
        BigDecimal amount = new BigDecimal("5000");
        BigDecimal resultBankAccountOne = bankAccountOne.getBalance();
        assertEquals(resultBankAccountOne, bankAccountOne.getBalance());
    }


    @Test
    @DisplayName("Perform A Zero Transfer")
    public void bankAccountZeroTransfer() {
        BigDecimal amount = new BigDecimal("0");
        BigDecimal resultBankAccountOne = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.transfer(bankAccountTwo, amount));
        assertEquals(resultBankAccountOne, bankAccountOne.getBalance());
    }

    @Test
    @DisplayName("Perform A Negative Transfer")
    public void bankAccountNegativeTransfer() {
        BigDecimal amount = new BigDecimal("-1000");
        BigDecimal resultBankAccountOne = bankAccountOne.getBalance();
        assertThrows(InvalidAmountException.class, () -> bankAccountOne.transfer(bankAccountTwo, amount));
        assertEquals(resultBankAccountOne, bankAccountOne.getBalance());
    }
}
