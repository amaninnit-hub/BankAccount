/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankaccount;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public abstract class BankAccount {

protected String owner;
protected String accountNumber;
protected double balance; 
protected int transactions;
protected ArrayList<String> transactionHistory = new ArrayList<>();

public BankAccount(String owner, String accountNumber, double balance){
    this.owner = owner;
    this.accountNumber = accountNumber; 
    this.balance = balance;
    this.transactions = 0;
}

protected String getTimeStamp(){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return now.format(formatter);
}

public void deposit(double amount){
    balance = balance + amount;
    transactions++;
    String record = "[" + getTimeStamp() + "] Deposited " + amount + " - Balance: " + balance;
    transactionHistory.add(record);
    System.out.println(record);
    
}

public void deposit(double amount, String description){
    balance = balance + amount; 
    transactions++;
    String record = "[" + getTimeStamp() + "] Deposited " + amount + " | Balance: " + balance + " for: " + description;
    transactionHistory.add(record);
    System.out.println(record);
}

public void withdraw(double amount){
    if (amount > balance){
        System.out.println("Insuffient funds.");
    } else {
        balance = balance - amount;
        transactions++;
        String record = "[" + getTimeStamp() + "] Withdrew " + amount + " | Balance: " + balance;
        transactionHistory.add(record);
        System.out.println(record);
    }
}

public void checkBalance(){
    System.out.println(owner + "'s balance: " + balance);
}

public void transfer(BankAccount target, double amount){
    if (amount > balance){
        System.out.println("Insufficient funds to transfer!");
    } else {
        this.balance = this.balance - amount;
        target.balance = target.balance + amount;
        transactions++;
        System.out.println(owner + "'s balance " + balance);
        String sentRecord = "[" + getTimeStamp() + "] Transferred " + amount + " to " + target.owner + " | Balance: " + balance;
        transactionHistory.add(sentRecord);
        String receivedRecord = "[" + getTimeStamp() + "] Received " + amount + " from " + owner + " | Balance: " + target.balance;
        target.transactionHistory.add(receivedRecord);
    }
}

public void viewTransactionHistory(){
    System.out.println("=== Transaction History for " + owner + " ===");
    if (transactionHistory.isEmpty()){
        System.out.println("No transactions yet.");
    } else {
        for (String record : transactionHistory){
            System.out.println(record);
        }
    }
}

public void accountInfo(){
    System.out.println("Owner: " + owner);
    System.out.println("Balance: " + balance);
    System.out.println("Account Number: " + accountNumber);
}



public abstract double calculateFee(); 
    
    public static void main(String[] args) {
    
    ArrayList<BankAccount> accounts = new ArrayList<>();    
    Scanner sc = new Scanner(System.in);
    boolean running = true;
    
    BankAccount selectedAccount = null;
    
        while(running){
            System.out.println("===== Bank Account =====");
            System.out.println("1. Create Account");
            System.out.println("2. Select Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Check Balance");
            System.out.println("7. View Transaction History");
            System.out.println("8. Apply Interest (Savings)");
            System.out.println("9. Exit");
            
            String choice = sc.nextLine().trim();
            
            switch(choice){
                case "1":
                    System.out.println("Enter Owner Name: ");
                    String owner = sc.nextLine().trim();
                    System.out.println("Enter Your Account Number: ");
                    String accNum = sc.nextLine().trim();
                    System.out.println("Enter Initial Balance: ");
                    double initBalance = Double.parseDouble(sc.nextLine().trim());
                    
                    System.out.println("What type of account would you like to create? Press 1 or 2: ");
                    System.out.println("=== 1) Premium Account ===");
                    System.out.println("=== 2) Savings Account ===");
                    String type = sc.nextLine().trim();
                        
                    if (type.equals("1")){
                        System.out.println("Credit Limit: ");
                    double credit = Double.parseDouble(sc.nextLine().trim());
                    accounts.add(new PremiumAccount(owner, accNum, initBalance, credit));
                        System.out.println("Premium account created!");
                    } else if (type.equals("2")){
                        System.out.println("Interest Rate: ");
                    double rate = Double.parseDouble(sc.nextLine().trim());
                    accounts.add(new SavingsAccount(owner, accNum, initBalance, rate));
                        System.out.println("Savings account created!");
                    } else {
                        System.out.println("Invalid account type.");
                    }
                    break;
                case "2":
                    System.out.println("Enter owner name: ");
                    String searchName = sc.nextLine().trim();
                    selectedAccount = findAccount(accounts, searchName);
                    if (selectedAccount != null){
                        System.out.println("Account selected! Welcome " + searchName);
                        selectedAccount.accountInfo();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case "3":
                    if (selectedAccount == null) {
                        System.out.println("Please select an account first (option 2).");
                    } else {
                        try {
                            System.out.println("Enter the amount you want to deposit: ");
                            double amount = Double.parseDouble(sc.nextLine().trim());
                            System.out.println("Description: ");
                            String desc = sc.nextLine().trim();
                            selectedAccount.deposit(amount, desc);
                        } catch (NumberFormatException e){
                            System.out.println("Invalid! Please enter a number.");
                        }
                    }
                    break;
                case "4":
                    if (selectedAccount == null) {
                        System.out.println("Please select an account first (option 2).");
                    } else {
                        try {
                            System.out.println("Enter the amount you want to withdraw: ");
                            double amount = Double.parseDouble(sc.nextLine().trim());
                            selectedAccount.withdraw(amount);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid! Please enter a number.");
                        }
                    }
                    break;
                case "5":
                    if (selectedAccount == null) {
                        System.out.println("Please select an account first (option 2).");
                    } else {
                        System.out.println("Please enter the owner's name: ");
                        String targetName = sc.nextLine().trim();
                        BankAccount targetAccount = findAccount(accounts, targetName);
                        if (targetAccount == null) {
                            System.out.println("Account not found.");
                        } else if (targetAccount == selectedAccount){
                            System.out.println("Cannot transfer to yourself.");
                        } else {
                            try {
                                System.out.println("Enter amount to transfer: ");
                                double amount = Double.parseDouble(sc.nextLine().trim());
                                selectedAccount.transfer(targetAccount, amount);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid! Please enter a number.");
                            }
                        }
                    }
                    break;
                case "6":
                    if (selectedAccount == null) {
                        System.out.println("Please select an account first (option 2).");
                    } else {
                        selectedAccount.checkBalance();
                    }
                    break;
                case "7":
                    if (selectedAccount == null) {
                        System.out.println("Please select an account first (option 2).");
                    } else {
                        selectedAccount.viewTransactionHistory();
                    }
                    break;
                case "8":
                    if (selectedAccount == null) {
                        System.out.println("Please select an account first (option 2).");
                    } else {
                        if (selectedAccount instanceof SavingsAccount) {
                            SavingsAccount sa = (SavingsAccount) selectedAccount;
                            sa.applyInterest();
                        } else {
                            System.out.println("This feature is only available for Savings accounts.");
                        }
                    }
                    break;
                case "9": 
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default: 
                    System.out.println("Invalid input. Please select from 1-9");
                    break;
            }
        }
    }
  
     static BankAccount findAccount(ArrayList<BankAccount> accounts, String owner){
            for (BankAccount account : accounts){
                if (account.owner.equals(owner)){
                    return account;
                } 
            }
            return null;
        }
}
   

