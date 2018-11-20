package com.teles.candidaturas.pessoas.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private final String field;

    private final String message;

    public static ErrorResponse newErrorResponse(FieldError fieldError) {

        return new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
