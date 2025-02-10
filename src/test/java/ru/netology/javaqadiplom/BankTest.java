package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {

    Bank bank = new Bank();
    Account savingAccount1 = new SavingAccount(
            2_000,
            1_000,
            10_000,
            15
    );
    Account creditAccount1 = new CreditAccount(
            2_000,
            10_000,
            15
    );

    @Test
    public void shouldReturnFalseWhenTransferWithNegativeAmount() {
        int amount = -1;

        Assertions.assertFalse(bank.transfer(savingAccount1, creditAccount1, amount));
    }

    @Test
    public void shouldReturnFalseWhenTransferWithZeroAmount() {
        int amount = 0;

        Assertions.assertFalse(bank.transfer(savingAccount1, creditAccount1, amount));
    }

    @Test
    public void shouldReturnTrueWhenTransferWithPositiveAmount() {
        int amount = 1;

        Assertions.assertTrue(bank.transfer(savingAccount1, creditAccount1, amount));
    }

    @Test
    public void shouldReturnFalseWhenTransferringToSameObject() {
        int amount = 100;

        Assertions.assertFalse(bank.transfer(savingAccount1, savingAccount1, amount));
    }

    @Test
    public void shouldBeSuccessWhenTransferring() {
        int amount = 100;
        boolean transferSuccess = bank.transfer(creditAccount1, savingAccount1, amount);

        int actualCreditBalance = creditAccount1.getBalance();
        int expectedCreditBalance = 2_000 - 100;

        int actualSavingBalance = savingAccount1.getBalance();
        int expectedSavingBalance = 2_000 + 100;

        Assertions.assertTrue(transferSuccess);
        Assertions.assertEquals(expectedCreditBalance,actualCreditBalance);
        Assertions.assertEquals(expectedSavingBalance,actualSavingBalance);
    }

    @Test
    public void shouldFailIfFromAccountInsufficientAndBalancesUnchanged() {
        int amount = 3_000;
        boolean transferSuccess = bank.transfer(savingAccount1, creditAccount1, amount);

        int actualSavingBalance = savingAccount1.getBalance();
        int expectedSavingBalance = 2_000;

        int actualCreditBalance = creditAccount1.getBalance();
        int expectedCreditBalance = 2_000;

        Assertions.assertFalse(transferSuccess);
        Assertions.assertEquals(expectedCreditBalance,actualCreditBalance);
        Assertions.assertEquals(expectedSavingBalance,actualSavingBalance);
    }

    @Test
    public void shouldFailIfToAccountInvalidAndBalancesUnchanged() {
        int amount = 11_000;
        boolean transferSuccess = bank.transfer(creditAccount1, savingAccount1, amount);

        int actualCreditBalance = creditAccount1.getBalance();
        int expectedCreditBalance = 2_000;

        int actualSavingBalance = savingAccount1.getBalance();
        int expectedSavingBalance = 2_000;

        Assertions.assertFalse(transferSuccess);
        Assertions.assertEquals(expectedCreditBalance,actualCreditBalance);
        Assertions.assertEquals(expectedSavingBalance,actualSavingBalance);
    }
}