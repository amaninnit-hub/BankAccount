/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankaccount;
import java.util.ArrayList;

public abstract class BankAccount {

protected String owner;
protected String accountNumber;
protected double balance; 
protected int transactions;

public BankAccount(String owner, String accountNumber, double balance){
    this.owner = owner;
    this.accountNumber = accountNumber; 
    this.balance = balance;
    this.transactions = 0;
}

public void deposit(double amount){
    balance = balance + amount;
    transactions++;
    System.out.println(owner + " deposited " + amount + "\nNew balance: " + balance);
}

public void deposit(double amount, String description){
    balance = balance + amount; 
    transactions++;
    System.out.println(owner + "deposited " + amount + "\nNew balance: " + balance + " \nFor" + description);
}

public void withdraw(double amount){
    if (amount > balance){
        System.out.println("Insufficient funds.");
    } else {
        balance = balance - amount;
        transactions++;
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
    }
}

public void accountInfo(){
    System.out.println("Owner: " + owner);
    System.out.println("Balance: " + balance);
}

public abstract double calculateFee();   
    
    public static void main(String[] args) {
        
        ArrayList<BankAccount> accounts = new ArrayList<>();
        
        accounts.add(new SavingsAccount("John", "SAV001", 1000.0, 5.0));
        accounts.add(new SavingsAccount("Sara", "SAV002", 500.0, 3.0));
        accounts.add(new PremiumAccount("Mike", "PRE001", 2000.0, 500.0));
        
        for (int i = 0; i < accounts.size(); i++){
            accounts.get(i).accountInfo();
            System.out.println("Fee: " + accounts.get(i).calculateFee());
            System.out.println("---");
        }
    }
  
     static void findAccount(ArrayList<BankAccount> accounts, String owner){
            for (BankAccount account : accounts){
                if (account.owner.equals(owner)){
                    System.out.println(owner + "'s Balance: " + account.balance);
                    return;
                } 
            }
            System.out.println("Account not found: " + owner);
        }
}
    

