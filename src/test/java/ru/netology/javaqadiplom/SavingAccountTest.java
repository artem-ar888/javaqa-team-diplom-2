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






}
