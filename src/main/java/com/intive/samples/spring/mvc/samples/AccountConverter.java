package com.intive.samples.spring.mvc.samples;


import org.springframework.core.convert.converter.Converter;

public class AccountConverter implements Converter<String, Account> {

    public AccountConverter(){
        System.out.println("init account converter...");
    }

    public Account convert(String accountNr) {
        Account account = new Account();
        account.setNumber(accountNr);
        account.setDesc("Account nr: " + accountNr);
        return account;
    }
}
