package com.learn.rest.webservices.restfulwebservices.advice;

import com.learn.rest.webservices.restfulwebservices.dto.ErrorDto;
import com.learn.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> generalException(Exception exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError(exception.getMessage());
        errorDto.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> userNotFoundException(UserNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError(exception.getMessage());
        errorDto.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDto errorDto = new ErrorDto();
        StringBuilder errors = new StringBuilder();
        ex.getFieldErrors().stream().forEach(
                fieldError -> errors.append(fieldError.getDefaultMessage()).append(", ")
        );
        errorDto.setError(String.format("Total error %d and error %s", ex.getErrorCount(), errors));
        errorDto.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
