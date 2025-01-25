package com.todo.FPOS_project.db.models.submodels;

import com.todo.FPOS_project.db.models.User;
import com.todo.FPOS_project.enums.Citizenship;

import java.time.LocalDate;

public class Investor extends User {
    
    private Citizenship citizenship;
    private String jobTitle, employerName, employerAddress, bankName, bankAccountNumber, IBAN;
    
    public Investor() {
    }

    public Investor(Citizenship citizenship, String jobTitle, String employerName, String employerAddress, String bankName, String bankAccountNumber, String IBAN) {
        this.citizenship = citizenship;
        this.jobTitle = jobTitle;
        this.employerName = employerName;
        this.employerAddress = employerAddress;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.IBAN = IBAN;
    }

    public Investor(String nationalId, String lastname, String firstname, String emailAdress, String hashedPassword, String phoneNumber, String address, String role, boolean enabled, LocalDate birthDate, Citizenship citizenship, String jobTitle, String employerName, String employerAddress, String bankName, String bankAccountNumber, String IBAN) {
        super(nationalId, lastname, firstname, emailAdress, hashedPassword, phoneNumber, address, role, enabled, birthDate);
        this.citizenship = citizenship;
        this.jobTitle = jobTitle;
        this.employerName = employerName;
        this.employerAddress = employerAddress;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.IBAN = IBAN;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}
