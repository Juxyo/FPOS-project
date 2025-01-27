package com.todo.FPOS_project.dtos.response;

import com.todo.FPOS_project.db.models.Property;

public class CatalogueResponseDTO {
    
    private Property property;
    private double investmentLeft;
    
    public CatalogueResponseDTO() {
    }
    
    public CatalogueResponseDTO(Property property, double investmentLeft) {
        this.property = property;
        this.investmentLeft = investmentLeft;
    }
    
    public Property getProperty() {
        return property;
    }
    
    public void setProperty(Property property) {
        this.property = property;
    }
    
    public double getInvestmentLeft() {
        return investmentLeft;
    }
    
    public void setInvestmentLeft(double investmentLeft) {
        this.investmentLeft = investmentLeft;
    }
    
}
