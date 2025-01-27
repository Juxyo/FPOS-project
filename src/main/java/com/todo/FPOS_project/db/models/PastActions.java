package com.todo.FPOS_project.db.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("PastActions")
public class PastActions {
    
    private LocalDate date;

    public PastActions() {
    }

    public PastActions(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
