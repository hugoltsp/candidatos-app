package com.teles.candidaturas.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private final String field;

    private final String message;

    public static ErrorResponse newErrorResponse(ObjectError error) {

        return new ErrorResponse(FieldError.class.isInstance(error) ? FieldError.class.cast(error).getField() : null,
                error.getDefaultMessage());
    }

}
