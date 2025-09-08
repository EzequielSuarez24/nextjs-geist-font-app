package com.example.universidad.exception;

import com.example.universidad.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), request.getRequestURI(), e.getMessage(), 404);
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), request.getRequestURI(), e.getMessage(), 400);
        return ResponseEntity.status(400).body(error);
    }
}
