package com.codsoft.atm.models;

public class Account {

    public Account(double amount){
        balance = amount;
    }
    public Account(){}
    private double balance;

    public double deposit(double amount){
        balance+=amount;
        return balance;
    }
    public double withdraw(double amount){
        if(balance-amount < 0)
            return -1;

        balance-=amount;
        return balance;
    }

    public double checkBalance(){
        return balance;
    }



}
