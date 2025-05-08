package com.rsoft.tdd_in_action.bankdomain;

import java.math.BigDecimal;


public class Account {

    private String holderName ;

    private  BigDecimal totalBalance;

    private String accountNumber;

    public Account(String accountNumber, String holderName, BigDecimal balance) {

        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.totalBalance = balance;
    }

    public Account(String accountNumber , String holderName){
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        totalBalance = new BigDecimal("0");
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
