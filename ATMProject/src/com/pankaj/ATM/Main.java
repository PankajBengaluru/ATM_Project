package com.pankaj.ATM;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = System.console();
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface();
        SecurityService securityService = new SecurityService();
        Account account = new Account(1000);
        TransactionService transactionService = new TransactionService(account, scanner);

        System.out.println("Welcome to the ATM!");

        String pin;
        if (console != null) {
            char[] pinArray = console.readPassword("Enter your PIN: ");
            pin = new String(pinArray);
        } else {
            System.out.print("Enter your PIN: ");
            pin = scanner.nextLine();
        }

        if (!securityService.authenticate(pin)) {
            System.out.println("Incorrect PIN. Access denied.");
            scanner.close();
            return;
        }

        boolean running = true;
        while (running) {
            ui.showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> transactionService.cashWithdrawal();
                case 2 -> transactionService.cashDeposit();
                case 3 -> transactionService.balanceInquiry();
                case 4 -> transactionService.fundTransfer();
                case 5 -> transactionService.billPayment();
                case 6 -> transactionService.mobileRecharge();
                case 7 -> transactionService.miniStatement();
                case 8 -> transactionService.chequeDeposit();
                case 9 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Thank you for using the ATM!");
        transactionService.close();
    }
}
