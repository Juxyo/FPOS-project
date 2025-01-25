package com.todo.FPOS_project.dtos.response;

import java.time.LocalDateTime;
import java.util.PrimitiveIterator;

public class UserLoginResponseDTO {

    private String value;
    private LocalDateTime expiresAt;

    public UserLoginResponseDTO() {
    }

    public UserLoginResponseDTO(String value, LocalDateTime expiresAt) {
        this.value = value;
        this.expiresAt = expiresAt;
    }

    public String getValue() {
        return value;
    }

    public UserLoginResponseDTO setValue(String value) {
        this.value = value;
        return this;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public UserLoginResponseDTO setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }
}
