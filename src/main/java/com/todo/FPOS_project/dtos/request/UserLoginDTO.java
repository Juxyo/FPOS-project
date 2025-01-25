package com.todo.FPOS_project.dtos.request;

public class UserLoginDTO {
    
    private String emailAdress, password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String emailAdress, String password) {
        this.emailAdress = emailAdress;
        this.password = password;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
