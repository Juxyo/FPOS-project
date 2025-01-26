package com.todo.FPOS_project.dtos.request;

import com.todo.FPOS_project.enums.PropertyState;
import com.todo.FPOS_project.enums.PropertyType;

import java.time.LocalDate;

public class PropertyCreateDTO {

    private String name, agentId, address;
    private double estimatedValue, annualRentIncomePercent, annualAppreciationPercent;
    private PropertyType type;

    public PropertyCreateDTO() {
    }

    public PropertyCreateDTO(String name, String agentId, String address, double estimatedValue, double annualRentIncomePercent, double annualAppreciationPercent, PropertyType type) {
        this.name = name;
        this.agentId = agentId;
        this.address = address;
        this.estimatedValue = estimatedValue;
        this.annualRentIncomePercent = annualRentIncomePercent;
        this.annualAppreciationPercent = annualAppreciationPercent;
        this.type = type;
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

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }
}
