package com.todo.FPOS_project.db.models;

import com.todo.FPOS_project.enums.TransactionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("Transactions")
public class Transaction {
    
    @Id
    private String id;
    private String investorId, propertyId;
    private double amount;
    private LocalDate date;
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(String investorId, String propertyId, double amount, TransactionType type) {
        this.investorId = investorId;
        this.propertyId = propertyId;
        this.amount = amount;
        this.type = type;
        this.date = LocalDate.now();
    }

    public Transaction(String id, String investorId, String propertyId, double amount, TransactionType type) {
        this.id = id;
        this.investorId = investorId;
        this.propertyId = propertyId;
        this.amount = amount;
        this.type = type;
        this.date = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
