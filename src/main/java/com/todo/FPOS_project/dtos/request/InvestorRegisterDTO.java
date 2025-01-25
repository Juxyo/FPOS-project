package com.todo.FPOS_project.dtos.request;

import com.todo.FPOS_project.enums.Citizenship;

import java.time.LocalDate;

public class InvestorRegisterDTO {

    private String nationalId, lastname, firstname, emailAdress, hashedPassword, phoneNumber, address, jobTitle, employerName, employerAddress, bankName, bankAccountNumber, IBAN;
    private LocalDate birthDate;
    private Citizenship citizenship;
    
    public InvestorRegisterDTO() {
    }

    public InvestorRegisterDTO(String nationalId, String lastname, String firstname, String emailAdress, String hashedPassword, String phoneNumber, String address, String jobTitle, String employerName, String employerAddress, String bankName, String bankAccountNumber, String IBAN, LocalDate birthDate, Citizenship citizenship) {
        this.nationalId = nationalId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.emailAdress = emailAdress;
        this.hashedPassword = hashedPassword;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.jobTitle = jobTitle;
        this.employerName = employerName;
        this.employerAddress = employerAddress;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.IBAN = IBAN;
        this.birthDate = birthDate;
        this.citizenship = citizenship;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }
}
