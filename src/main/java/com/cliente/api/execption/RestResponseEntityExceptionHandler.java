package com.cliente.api.execption;

import com.cliente.api.dto.FieldMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldMessage> fieldMessages = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            fieldMessages.add(new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return handleExceptionInternal(ex, fieldMessages, headers, HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusExceptionn(ResponseStatusException ex, WebRequest request) {
        FieldMessage fieldMessage = new FieldMessage("null", ex.getReason());
        return new ResponseEntity(fieldMessage, HttpStatus.BAD_REQUEST);
    }


}
