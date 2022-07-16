package com.example.bankingapp;

public class Model {
    String Name,PhoneNo,EmailID,balance;

    public Model(String Name,String phoneNo,String balance){
        this.Name=Name;
        this.PhoneNo=phoneNo;
        this.balance=balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBalance() {
        return balance;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }
}
