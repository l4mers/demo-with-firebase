package com.example.demowithfirebase.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleNotAllowedException(NotAllowedException ex, WebRequest request) {

        logger.error("NotAllowedException occurred: {} | Path: {} | Request Params: {}",
                ex.getMessage(),
                request.getDescription(false),
                request.getParameterMap());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
