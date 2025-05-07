package com.rsoft.tdd_in_action.bankdomain;
import com.rsoft.tdd_in_action.bankdomain.exception.CustomException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankManage {

    public static Map<String, Account> accountNumberToAccountMap = new ConcurrentHashMap<>();


    public  void createAccount(Account account) {
        accountNumberToAccountMap.put(account.getAccountNumber(), account);
    }

    public  BigDecimal  deposite(BigDecimal bigDecimal, String accountNumber) {

        Account account = accountNumberToAccountMap.get(accountNumber);
         account.setTotalBalance(account.getTotalBalance().add(bigDecimal));

         return account.getTotalBalance();


    }

    public BigDecimal withDraw(BigDecimal amount, String accountNumber) {

        Account account = accountNumberToAccountMap.get(accountNumber);
        if(account.getTotalBalance().compareTo(amount) < 0){
            throw new CustomException("balance is not suffecient");
        }
        account.setTotalBalance(account.getTotalBalance().subtract(amount));
        return account.getTotalBalance();
    }
}
