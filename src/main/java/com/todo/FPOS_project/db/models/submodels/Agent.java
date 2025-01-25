package com.todo.FPOS_project.db.models.submodels;

import com.todo.FPOS_project.db.models.User;

import java.time.LocalDate;

public class Agent extends User {

    public Agent() {
    }

    public Agent(String nationalId, String lastname, String firstname, String emailAdress, String hashedPassword, String phoneNumber, String address, String role, boolean enabled, LocalDate birthDate) {
        super(nationalId, lastname, firstname, emailAdress, hashedPassword, phoneNumber, address, role, enabled, birthDate);
    }
}
