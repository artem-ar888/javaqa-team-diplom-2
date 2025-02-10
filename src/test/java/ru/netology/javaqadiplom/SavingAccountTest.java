package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    SavingAccount account = new SavingAccount(
            2_000,
            1_000,
            10_000,
            5
    );
    @Test
    public void shouldAddLessThanMaxBalance() {
        account.add(3_000);
        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }
    @Test
    public void shouldNotAddMoreThanMaxBalance(){
        account.add(11_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }
    @Test
    public void shouldNotAddNegativeAmount(){
        account.add(-3_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }
    @Test
    public void shouldNotAddZeroAmount(){
        account.add(0);
        Assertions.assertEquals(2_000, account.getBalance());
    }
    @Test
    public void shouldPayLessThanMinBalance(){
        account.pay(900);
        Assertions.assertEquals(1_100, account.getBalance());
    }
    @Test
    public void shouldNotPayMoreThanMinBalance(){
        account.pay(1_100);
        Assertions.assertEquals(2_000, account.getBalance());
    }
    @Test
    public void shouldPayEqualMinBalance(){
        account.pay(1_000);
        Assertions.assertEquals(1_000, account.getBalance());
    }
    @Test
    public void shouldNotPayWhenAmountZero(){
        account.pay(0);
        Assertions.assertEquals(2_000, account.getBalance());
    }
    @Test
    public void shouldNotPayWhenAmountNegative(){
        account.pay(-100);
        Assertions.assertEquals(2_000, account.getBalance());
    }
    @Test
    public void shouldNotSavingAccWhenRateIsNegative(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(2_000,
                1_000, 10_000, -5));
    }
    @Test
    public void shouldNotSavingAccWhenMinBalanceMoreThanMaxBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(2_000,
                10_000, 1_000, 5));
    }
    @Test
    public void shouldNotSavingAccWhenBalanceLessThanMinBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(100,
                2_000, 10_000, 5));
    }
    @Test
    public void shouldNotSavingAccWhenBalanceMoreThanMaxBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(20_000,
                1_000, 10_000, 5));
    }
    @Test
    public void shouldSavingAccWithZeroBalance(){
        Assertions.assertDoesNotThrow(() -> new SavingAccount(0,
                0, 10_000, 15));
    }
    @Test
    public void shouldNotSavingAccWithNegativeMinBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1_000,
                -1_000, 10_000, 5)); //bag-report
    }
    @Test
    public void shouldNotSavingAccWithNegativeMaxBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1_000,
                1_000, -10_000, 5)); //bag-report
    }
    @Test
    public void shouldNotPayMoreThanBalance(){
        account.pay(5_000);
        Assertions.assertEquals(2_000, account.getBalance());  // bag-report
    }
    @Test
    public void shouldCalculateYearChange(){
        SavingAccount account2 = new SavingAccount(399,
                0, 10_000, 10);
        Assertions.assertEquals(39, account2.yearChange());
    }
    @Test
    public void shouldCalculateYearChangeWithZeroBalance(){
        SavingAccount account3 = new SavingAccount(0,
                0, 10_000, 10);
        Assertions.assertEquals(0, account3.yearChange());
    }
    @Test
    public void shouldSavingAccWhenBalanceMinBalanceMaxBalanceEquals(){
        Assertions.assertDoesNotThrow(() -> new SavingAccount(100,
                100, 100 , 5));
    }
    @Test
    public void shouldNotSetNegativeRate(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.setRate(-5));
    }
    @Test
    public void shouldSetRate(){
        Assertions.assertDoesNotThrow(() -> account.setRate(20));
    }
    @Test
    public void shouldNotSavingAccWithNegativeInitialBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1_000,
                1_000, 10_000, 10));
    }
    @Test
    public void shouldNotSavingAccWithZeroMaxBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(0,
                0, 0, 10));
    }
}
