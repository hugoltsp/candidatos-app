package com.teles.candidatos.vagas.api.exception.handler;

import com.teles.candidatos.vagas.api.domain.dto.ErrorResponse;
import com.teles.candidatos.vagas.api.exception.VagaNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class VagaApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e) {
        log.error("Error.", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(VagaNotFoundException.class)
    public ResponseEntity<?> vagaNotFoundException(VagaNotFoundException e) {
        log.error("Unable to retrieve some data.", e);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        return ResponseEntity.badRequest().body(ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse::newErrorResponse)
                .collect(Collectors.toList()));
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
