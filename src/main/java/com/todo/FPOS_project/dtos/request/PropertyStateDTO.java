package com.todo.FPOS_project.dtos.request;

import com.todo.FPOS_project.enums.PropertyState;

public class PropertyStateDTO {
    
    String state;
    
    public PropertyStateDTO() {}
    
    public PropertyStateDTO(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }
    
    public PropertyStateDTO setState(String state) {
        this.state = state;
        return this;
    }
}
