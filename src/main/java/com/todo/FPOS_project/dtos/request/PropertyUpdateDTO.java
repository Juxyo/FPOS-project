package com.todo.FPOS_project.dtos.request;

import com.todo.FPOS_project.enums.PropertyType;

import java.util.Optional;
import java.util.OptionalDouble;

public class PropertyUpdateDTO {

    private Optional<String> name, agentId, address;
    private OptionalDouble estimatedValue, annualRentIncomePercent, annualAppreciationPercent;
    private Optional<PropertyType> type;

    public PropertyUpdateDTO() {
    }

    public PropertyUpdateDTO(Optional<String> name, Optional<String> agentId, Optional<String> address, OptionalDouble estimatedValue, OptionalDouble annualRentIncomePercent, OptionalDouble annualAppreciationPercent, Optional<PropertyType> type) {
        this.name = name;
        this.agentId = agentId;
        this.address = address;
        this.estimatedValue = estimatedValue;
        this.annualRentIncomePercent = annualRentIncomePercent;
        this.annualAppreciationPercent = annualAppreciationPercent;
        this.type = type;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getAgentId() {
        return agentId;
    }

    public void setAgentId(Optional<String> agentId) {
        this.agentId = agentId;
    }

    public Optional<String> getAddress() {
        return address;
    }

    public void setAddress(Optional<String> address) {
        this.address = address;
    }

    public OptionalDouble getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(OptionalDouble estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public OptionalDouble getAnnualRentIncomePercent() {
        return annualRentIncomePercent;
    }

    public void setAnnualRentIncomePercent(OptionalDouble annualRentIncomePercent) {
        this.annualRentIncomePercent = annualRentIncomePercent;
    }

    public OptionalDouble getAnnualAppreciationPercent() {
        return annualAppreciationPercent;
    }

    public void setAnnualAppreciationPercent(OptionalDouble annualAppreciationPercent) {
        this.annualAppreciationPercent = annualAppreciationPercent;
    }

    public Optional<PropertyType> getType() {
        return type;
    }

    public void setType(Optional<PropertyType> type) {
        this.type = type;
    }
}
