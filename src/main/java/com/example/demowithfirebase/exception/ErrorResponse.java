package com.example.demowithfirebase.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String message;
    private String details;
    private LocalDateTime timestamp;
    private int status;
    private String errorCode;
}
