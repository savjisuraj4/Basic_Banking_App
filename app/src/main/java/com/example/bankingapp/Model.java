package com.example.bankingapp;

public class Model {
    String Name,PhoneNo,balance,from_name,To_name,date,transaction_status;

    public Model(String Name,String phoneNo,String balance){
        this.Name=Name;
        this.PhoneNo=phoneNo;
        this.balance=balance;
    }
    public Model(String from_name,String To_name,String balance,String date,String transaction_status){
        this.from_name=from_name;
        this.transaction_status=transaction_status;
        this.To_name=To_name;
        this.date=date;
        this.balance=balance;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTo_name(String to_name) {
        To_name = to_name;
    }

    public String getTo_name() {
        return To_name;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
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
