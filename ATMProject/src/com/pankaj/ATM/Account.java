package com.pankaj.ATM;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private List<String> transactionHistory;

    public Account(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdrawal: ₹" + amount);
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposit: ₹" + amount);
        }
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void printMiniStatement() {
        System.out.println("Mini Statement:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.printf("Current balance: ₹%.2f%n", balance);
    }
}
