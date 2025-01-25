package com.todo.FPOS_project.db.models;

import com.todo.FPOS_project.enums.TransactionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Transactions")
public class Transaction {
    
    @Id
    private String id;
    private String userId, propertyId;
    private double amount;
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(String id, String userId, String propertyId, double amount, TransactionType type) {
        this.id = id;
        this.userId = userId;
        this.propertyId = propertyId;
        this.amount = amount;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
