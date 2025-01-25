package com.todo.FPOS_project.db.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.management.loading.PrivateClassLoader;

@Document("Shares")
public class Share {
    
    private String investorId, propertyId;
    private double sharePercent;

    public Share() {
    }

    public Share(String investorId, String propertyId, double sharePercent) {
        this.investorId = investorId;
        this.propertyId = propertyId;
        this.sharePercent = sharePercent;
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

    public double getSharePercent() {
        return sharePercent;
    }

    public void setSharePercent(double sharePercent) {
        this.sharePercent = sharePercent;
    }
}
