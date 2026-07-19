# Bank Management System

A Java console application simulating a real bank management system, built to demonstrate all four OOP pillars and clean software design.

## Features
- Create Savings and Premium bank accounts dynamically
- Deposit with description and timestamp
- Withdraw with balance validation
- Transfer between accounts with insufficient funds check
- View account balance and details
- Full transaction history with timestamps for every account
- Apply interest for Savings accounts
- Credit limit systen for Premium accounts
- Interactive menu with input validation

## OOP Concepts Demonstrated
- Encapsulation - protected fields accessed through methods
- Inheritance - SavingsAccount and PremiumAccount extend BankAccount
- Polymorphism - method overriding and overloading across account types
- Abstraction - abstract class with abstract calculateFee() method
- Interface - Transferable interface for transfer and loan behaviour
- instanceof and casting for type-specific features

## Tech Stack 
- Java (Apache NetBeans)
- No external dependencies
  
## How to Run
1. Clone the repository
2. Open in NetBeans
3. Run BankAccount.java
4. Use the interactive menu to manage accounts
