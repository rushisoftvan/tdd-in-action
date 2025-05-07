package com.rsoft.tdd_in_action.bankdomain;


import com.rsoft.tdd_in_action.bankdomain.exception.CustomException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;


class BankManageTest {

    @Test
    public void should_create_bank_account(){
        Account trup = new Account(UUID.randomUUID().toString(), "trup", new BigDecimal("500"));
        Account davik = new Account(UUID.randomUUID().toString(), "davik", new BigDecimal("500"));
        BankManage bankManage = new BankManage();

        bankManage.createAccount(trup);
        bankManage.createAccount(davik);
        Assertions.assertThat(BankManage.accountNumberToAccountMap.size()).isEqualTo(2);

    }

//    @Test
//    public void should_create_bank_account_if_name_should_be_dublicate(){
//        Account trup = new Account(UUID.randomUUID().toString(), "trup", new BigDecimal("500"));
//        BankManage bankManage = new BankManage();
//
//        Assertions.assertThatThrownBy(() -> bankManage.createAccount(null))
//                .isInstanceOf(CustomException.class)
//                .hasMessage("account holder");
//    }

    @Test
    public void should_deposit_bank_account(){
        Account trup = new Account(UUID.randomUUID().toString(), "trup", new BigDecimal("500"));
        BankManage bankManage = new BankManage();
        bankManage.createAccount(trup);
        BigDecimal balance = bankManage.deposite(new BigDecimal("100"), trup.getAccountNumber());
        Assertions.assertThat(balance).isEqualTo("600");
    }

    @Test
    public void should_withdraw_from_bank_account(){
        Account trup = new Account(UUID.randomUUID().toString(), "trup", new BigDecimal("500"));
        BankManage bankManage = new BankManage();
        bankManage.createAccount(trup);
        BigDecimal balance = bankManage.withDraw(new BigDecimal("100"), trup.getAccountNumber());
        Assertions.assertThat(balance).isEqualTo("400");
    }

    @Test
    public void should_throw_exception_withdraw_balance_insufficient_account(){
        Account trup = new Account(UUID.randomUUID().toString(), "trup", new BigDecimal("500"));
        BankManage bankManage = new BankManage();
        bankManage.createAccount(trup);
        Assertions.assertThatThrownBy(()-> bankManage.withDraw(new BigDecimal("600") , trup.getAccountNumber())).
                isInstanceOf(CustomException.class)
                .hasMessage("balance is not suffecient");
    }

}