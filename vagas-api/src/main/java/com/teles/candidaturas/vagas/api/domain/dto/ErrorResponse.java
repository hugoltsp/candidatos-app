package com.teles.candidaturas.vagas.api.domain.dto;

import lombok.Data;
import org.springframework.validation.FieldError;

@Data
public class ErrorResponse {

    private final String field;
    
    private final String message;

    public static ErrorResponse newErrorResponse(FieldError fieldError) {

        return new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
