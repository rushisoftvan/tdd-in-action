package com.rsoft.tdd_in_action.bankdomain;

public enum BankProcessTypeEnum {

    DEPOSITE("deposite") ,

    WITHDRAW("withdraw") ;

    private String value;

     BankProcessTypeEnum(String value){
        this.value = value;
    }

    public String getValue(){
         return value;
    };


}
