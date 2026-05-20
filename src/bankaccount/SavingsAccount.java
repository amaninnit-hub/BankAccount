/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount;

public class SavingsAccount extends BankAccount{
    
    protected double interestRate; 
    
    public SavingsAccount(String owner, String accountNumber, double balance, double interestRate){
        this.interestRate = interestRate;
        super(owner, accountNumber, balance);
    }
    
    public void applyInterest(){
        double interest = balance * interestRate / 100;
        balance = balance + interest;
        transactions++;
        System.out.println("Interest applied! New balance: " + balance);
    }
    
    @Override
    public void accountInfo(){
        System.out.println("Owner: " + owner);
        System.out.println("Balance: " + balance);
        System.out.println("Interest rate: " + interestRate);
    }
    
    @Override
    public double calculateFee(){
        return balance * 1 / 100;
    }
}
