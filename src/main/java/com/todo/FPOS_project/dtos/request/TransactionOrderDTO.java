package com.todo.FPOS_project.dtos.request;

public class TransactionOrderDTO {
    
    private String investorId;
    private double amount;

    public TransactionOrderDTO() {
    }

    public TransactionOrderDTO(String investorId, double amount) {
        this.investorId = investorId;
        this.amount = amount;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
