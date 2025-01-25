package com.todo.FPOS_project.db.models;

import com.todo.FPOS_project.enums.PropertyState;
import com.todo.FPOS_project.enums.PropertyType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("Properties")
public class Property {
    @Id
    private String id;
    private String name, agentId, address;
    private double estimatedValue, annualRentIncomePercent, annualAppreciationPercent;
    private LocalDate creationDate, lastFoundingStartDate;
    private PropertyState state;
    private PropertyType type;
    
    public Property() {
    }
    
    public Property(String name, String agentId, String address, double estimatedValue, double annualRentIncomePercent, double annualAppreciationPercent, LocalDate creationDate, LocalDate lastFoundingStartDate, PropertyState state, PropertyType type) {
        this.name = name;
        this.agentId = agentId;
        this.address = address;
        this.estimatedValue = estimatedValue;
        this.annualRentIncomePercent = annualRentIncomePercent;
        this.annualAppreciationPercent = annualAppreciationPercent;
        this.creationDate = creationDate;
        this.lastFoundingStartDate = lastFoundingStartDate;
        this.state = state;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public double getAnnualRentIncomePercent() {
        return annualRentIncomePercent;
    }

    public void setAnnualRentIncomePercent(double annualRentIncomePercent) {
        this.annualRentIncomePercent = annualRentIncomePercent;
    }

    public double getAnnualAppreciationPercent() {
        return annualAppreciationPercent;
    }

    public void setAnnualAppreciationPercent(double annualAppreciationPercent) {
        this.annualAppreciationPercent = annualAppreciationPercent;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastFoundingStartDate() {
        return lastFoundingStartDate;
    }

    public void setLastFoundingStartDate(LocalDate lastFoundingStartDate) {
        this.lastFoundingStartDate = lastFoundingStartDate;
    }

    public PropertyState getState() {
        return state;
    }

    public void setState(PropertyState state) {
        this.state = state;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }
}
