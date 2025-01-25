package com.todo.FPOS_project.db.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Wallets")
public class Wallet {
    @Id
    private String investorId;
    private double balance;
    
    public Wallet() {
    }
    
    public Wallet(String investorId, double balance) {
        this.investorId = investorId;
        this.balance = balance;
    }
    
    public String getInvestorId() {
        return investorId;
    }
    
    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
