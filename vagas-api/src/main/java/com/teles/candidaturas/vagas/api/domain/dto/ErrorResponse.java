package com.teles.candidaturas.vagas.api.domain.dto;

import lombok.Data;
import org.springframework.validation.FieldError;

@Data
public class ErrorResponse {

    private String field;
    private String message;

    public static ErrorResponse newErrorResponse(FieldError fieldError){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(fieldError.getDefaultMessage());
        errorResponse.setField(fieldError.getField());

        return errorResponse;
    }

}
