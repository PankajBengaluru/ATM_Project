package com.pankaj.ATM;

import java.util.Scanner;

public class TransactionService {
    private Account account;
    private Scanner scanner;

    public TransactionService(Account account, Scanner scanner) {
        this.account = account;
        this.scanner = scanner;
    }

    public void cashWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }

        if (account.withdraw(amount)) {
            System.out.printf("Please, collect your cash. Your remaining balance is: ₹%.2f%n", account.getBalance());
        } else {
            System.out.println("Insufficient funds.");
            System.out.printf("Current balance: ₹%.2f%n", account.getBalance());
        }
    }

    public void cashDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }

        account.deposit(amount);
        System.out.printf("Deposit successful. Your current balance is: ₹%.2f%n", account.getBalance());
    }

    public void balanceInquiry() {
        System.out.printf("Current balance: ₹%.2f%n", account.getBalance());
    }

    public void fundTransfer() {
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Transfer amount must be greater than zero.");
            return;
        }

        System.out.print("Enter recipient account number: ");
        String recipient = scanner.next();

        if (account.withdraw(amount)) {
            account.addTransaction("Fund Transfer: ₹" + amount + " to account " + recipient);
            System.out.println("Transferred ₹" + amount + " to account: " + recipient);
            System.out.printf("Current balance: ₹%.2f%n", account.getBalance());
        } else {
            System.out.println("Insufficient funds for transfer.");
            System.out.printf("Current balance: ₹%.2f%n", account.getBalance());
        }
    }

    public void billPayment() {
        System.out.print("Enter bill amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Bill amount must be greater than zero.");
            return;
        }

        if (account.withdraw(amount)) {
            account.addTransaction("Bill Payment: ₹" + amount);
            System.out.println("Bill payment of ₹" + amount + " processed.");
        } else {
            System.out.printf("Insufficient funds for bill payment. Current balance: ₹%.2f%n", account.getBalance());
        }
    }

    public void mobileRecharge() {
        System.out.print("Enter your 10-digit mobile number: ");
        String mobileNumber = scanner.next();

        if (!mobileNumber.matches("\\d{10}")) {
            System.out.println("Invalid mobile number. Please enter a 10-digit number.");
            return;
        }

        System.out.println("Choose your mobile operator:");
        System.out.println("1. Airtel");
        System.out.println("2. Jio");
        System.out.println("3. Vi (Vodafone Idea)");
        System.out.println("4. BSNL");
        System.out.println("5. MTNL (Delhi and Mumbai)");
        
        System.out.print("Enter your choice (1-5): ");
        int operatorChoice = scanner.nextInt();
        
        String operator;
        switch (operatorChoice) {
            case 1 -> operator = "Airtel";
            case 2 -> operator = "Jio";
            case 3 -> operator = "Vi (Vodafone Idea)";
            case 4 -> operator = "BSNL";
            case 5 -> operator = "MTNL (Delhi and Mumbai)";
            default -> {
                System.out.println("Invalid choice. Please select a valid operator.");
                return;
            }
        }

        System.out.print("Enter recharge amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Recharge amount must be greater than zero.");
            return;
        }

        if (account.withdraw(amount)) {
            account.addTransaction("Mobile Recharge: ₹" + amount + " for " + operator + " (number: " + mobileNumber + ")");
            System.out.printf("Mobile recharge of ₹%.2f for %s (number: %s) processed.%n", amount, operator, mobileNumber);
        } else {
            System.out.printf("Insufficient funds for mobile recharge. Current balance: ₹%.2f%n", account.getBalance());
        }
    }

    public void miniStatement() {
        account.printMiniStatement();
    }

    public void chequeDeposit() {
        System.out.print("Enter cheque amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Cheque amount must be greater than zero.");
            return;
        }

        account.deposit(amount);
        account.addTransaction("Cheque Deposit: ₹" + amount);
        System.out.println("Cheque deposited successfully.");
        System.out.printf("Current balance: ₹%.2f%n", account.getBalance());
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
