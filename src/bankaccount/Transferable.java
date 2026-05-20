/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bankaccount;

public interface Transferable {
void transfer(BankAccount target, double amount);
void requestLoan(double amount);
}
