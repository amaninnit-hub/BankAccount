/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount;

public class PremiumAccount extends BankAccount implements Transferable{
    protected double creditLimit;
    
    public PremiumAccount(String owner, String accountNumber, double balance, double creditLimit){
        super(owner, accountNumber, balance);
        this.creditLimit = creditLimit;
    }
    
    public void useCreditLimit(double amount){
        if (amount <= balance){
            balance = balance - amount; 
            transactions++;
            System.out.println("Spent" + amount + "\nNew balance: " + balance);
        } else if (amount <= balance + creditLimit){
            balance = balance - amount; 
            transactions++;
            System.out.println("Spent " + amount + " using credit.\nBalance: " + balance);
        } else { 
            System.out.println("Exceed your credit limit! Max you can spend: " + (balance + creditLimit));
        }
    }
   
    @Override
    public void accountInfo(){
        System.out.println("Owner: " + owner);
        System.out.println("Balance: " + balance);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Credit limit: " + creditLimit);
    }
    
    @Override
    public double calculateFee(){
        return 50.0; 
    }
  
    public void requestLoan(double amount){
        System.out.println(owner + " requested a loan of " + amount);
    }
}