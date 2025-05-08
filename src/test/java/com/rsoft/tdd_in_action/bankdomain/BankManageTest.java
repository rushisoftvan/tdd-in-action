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

    @Test
    public void should_print_transaction_statement(){
        BankManage bankManage = new BankManage();
        Account trup = new Account(UUID.randomUUID().toString(), "trup");
        bankManage.createAccount(trup);
        bankManage.deposite(new BigDecimal("500"), trup.getAccountNumber());
        bankManage.withDraw(new BigDecimal(200), trup.getAccountNumber());

        String printStatment = bankManage.printStatment();
        Assertions.assertThat(printStatment).isEqualTo("""
                DATE       | AMOUNT | PROCESS | BALANCE 
                14/05/2025 | 500  | DEPOSITE | 500 
                15/05/2025 | 200  | WITHDRAW | 300
                
                """);

    }
}