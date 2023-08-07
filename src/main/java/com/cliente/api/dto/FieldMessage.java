package com.cliente.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FieldMessage {

    private String field;
    private String message;

    public FieldMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public FieldMessage() {
    }
    
}
