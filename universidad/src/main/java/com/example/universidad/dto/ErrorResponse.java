package com.example.universidad.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private String path;
    private String message;
    private int code;

    public ErrorResponse(LocalDateTime timestamp, String path, String message, int code) {
        this.timestamp = timestamp;
        this.path = path;
        this.message = message;
        this.code = code;
    }

    // getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
