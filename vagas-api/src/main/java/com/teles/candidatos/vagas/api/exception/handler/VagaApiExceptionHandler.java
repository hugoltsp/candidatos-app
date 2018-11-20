package com.teles.candidatos.vagas.api.exception.handler;

import com.teles.candidatos.vagas.api.domain.dto.ErrorResponse;
import com.teles.candidatos.vagas.api.exception.VagaNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class VagaApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> exception(Exception e) {
        log.error("Error.", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(value = {VagaNotFoundException.class})
    public ResponseEntity<?> vagaNotFoundException(VagaNotFoundException e) {
        log.error("An error ocurred while trying to retrieve some data.", e);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        log.error("Error", ex);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
