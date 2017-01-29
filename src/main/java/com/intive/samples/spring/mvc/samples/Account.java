package com.intive.samples.spring.mvc.samples;


public class Account {

    public Account(){
        System.out.println("init account..");
    }

    private String number;

    private String desc;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
