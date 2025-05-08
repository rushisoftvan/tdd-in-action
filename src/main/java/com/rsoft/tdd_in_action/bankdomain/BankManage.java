package com.rsoft.tdd_in_action.bankdomain;

import com.rsoft.tdd_in_action.bankdomain.exception.CustomException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankManage {

    public static Map<String, Account> accountNumberToAccountMap = new ConcurrentHashMap<>();

    List<String> statmentLine = new ArrayList<>();


    public  void createAccount(Account account) {
        accountNumberToAccountMap.put(account.getAccountNumber(), account);
    }

    public  BigDecimal  deposite(BigDecimal bigDecimal, String accountNumber) {


        Account account = accountNumberToAccountMap.get(accountNumber);
         account.setTotalBalance(account.getTotalBalance().add(bigDecimal));
         statmentLine.add(String.format("%s | %s  | %s | %s", "14/05/2025", bigDecimal ,
                 BankProcessTypeEnum.DEPOSITE,account.getTotalBalance()));

         return account.getTotalBalance();


    }

    public BigDecimal withDraw(BigDecimal amount, String accountNumber) {

        Account account = accountNumberToAccountMap.get(accountNumber);
        checkBalanceSufficient(amount, account);
        account.setTotalBalance(account.getTotalBalance().subtract(amount));
        statmentLine.add(String.format("%s | %s  | %s | %s", "15/05/2025",  amount ,BankProcessTypeEnum.WITHDRAW,
                account.getTotalBalance()));
        return account.getTotalBalance();
    }

    private  void checkBalanceSufficient(BigDecimal amount, Account account) {
        if(account.getTotalBalance().compareTo(amount) < 0){
            throw new CustomException("balance is not suffecient");
        }
    }

    public String printStatment() {

        StringBuilder bankStatment =  new StringBuilder(""" 
DATE       | AMOUNT | PROCESS | BALANCE 
""");

        for(String line : statmentLine){
            bankStatment.append(line).append("\n");
        }
        return  bankStatment.append('\n').toString();
    }
}
