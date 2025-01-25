package com.todo.FPOS_project.dtos.request;

import com.todo.FPOS_project.enums.Citizenship;

import java.time.LocalDate;

public class AgentRegisterDTO {

    private String nationalId, lastname, firstname, emailAdress, hashedPassword, phoneNumber, address;
    private boolean enabled;
    private LocalDate birthDate;

    public AgentRegisterDTO() {
    }

    public AgentRegisterDTO(String nationalId, String lastname, String firstname, String emailAdress, String hashedPassword, String phoneNumber, String address, boolean enabled, LocalDate birthDate) {
        this.nationalId = nationalId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.emailAdress = emailAdress;
        this.hashedPassword = hashedPassword;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.enabled = enabled;
        this.birthDate = birthDate;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
