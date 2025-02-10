package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    CreditAccount simpleAccount = new CreditAccount(
            100,
            5_000,
            15
    );

    @Test
    public void validateNegativeInitialBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -1,
                    5_000,
                    15);
        });
    }

    @Test
    public void validateZeroInitialBalance() {
        Assertions.assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(
                    0,
                    5_000,
                    15);
        });
    }

    @Test
    public void validatePositiveInitialBalance() {
        Assertions.assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(
                    1,
                    5_000,
                    15);
        });
    }

    @Test
    public void validateNegativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    10,
                    -1,
                    15);
        });
    }

    @Test
    public void validateZeroCreditLimit() {
        Assertions.assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(
                    10,
                    0,
                    15);
        });
    }

    @Test
    public void validatePositiveCreditLimit() {
        Assertions.assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(
                    10,
                    1,
                    15);
        });
    }

    @Test
    public void validateNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    10,
                    5_000,
                    -1);
        });
    }

    @Test
    public void validateZeroRate() {
        Assertions.assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(
                    10,
                    5_000,
                    0);
        });
    }

    @Test
    public void validatePositiveRate() {
        Assertions.assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(
                    10,
                    5_000,
                    1);
        });
    }

    @Test
    public void shouldReturnFalseWhenPayingWithNegativeAmount() {
        int amount = -1;

        Assertions.assertFalse(simpleAccount.pay(amount));
    }

    @Test
    public void shouldReturnFalseWhenPayingWithZeroAmount() {
        int amount = 0;

        Assertions.assertFalse(simpleAccount.pay(amount));
    }

    @Test
    public void shouldReturnTrueAndSetBalanceToCreditLimitWhenPaying() {
        int amount = 5_100;
        boolean purchaseSuccess = simpleAccount.pay(amount);

        int actual = simpleAccount.getBalance();
        int expected = 100 - 5_100;

        Assertions.assertTrue(purchaseSuccess);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrueAndChangeBalanceWhenPaying() {
        int amount = 5_099;
        boolean purchaseSuccess = simpleAccount.pay(amount);

        int actual = simpleAccount.getBalance();
        int expected = 100 - 5_099;

        Assertions.assertTrue(purchaseSuccess);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnFalseAndDoesNotChangeBalanceWhenPaying() {
        int amount = 5_101;
        boolean purchaseSuccess = simpleAccount.pay(amount);

        int actual = simpleAccount.getBalance();
        int expected = 100;

        Assertions.assertFalse(purchaseSuccess);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnFalseWhenAddingWithNegativeAmount() {
        int amount = -1;

        Assertions.assertFalse(simpleAccount.add(amount));
    }

    @Test
    public void shouldReturnFalseWhenAddingWithZeroAmount() {
        int amount = 0;

        Assertions.assertFalse(simpleAccount.add(amount));
    }

    @Test
    public void shouldReturnTrueWhenAddingWithPositiveAmount() {
        int amount = 1;

        Assertions.assertTrue(simpleAccount.add(amount));
    }

    @Test
    public void shouldChangeBalanceWhenAdding() {
        int amount = 100;
        simpleAccount.add(amount);

        int actual = simpleAccount.getBalance();
        int expected = 100 + 100;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCountYearChangeBecauseBalanceIsNegative() {
        simpleAccount.pay(300);

        int actual = simpleAccount.yearChange();
        int expected = -30; // (-200 * 15) / 100

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotCountYearChangeBecauseBalanceIsZero() {
        simpleAccount.pay(100);

        int actual = simpleAccount.yearChange();
        int expected = 0; // balance = 0

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotCountYearChangeBecauseBalanceIsPositive() {
        int actual = simpleAccount.yearChange();
        int expected = 0; // balance = 100 > 0

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCountYearChangeAndTruncate() {
        simpleAccount.pay(299);

        int actual = simpleAccount.yearChange();
        int expected = -29; // (-199 * 15) / 100

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSetNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                simpleAccount.setRate(-1));
    }
}