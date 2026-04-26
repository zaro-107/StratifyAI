package com.stratifyai.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Catch file upload errors (file too large, wrong format)
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Map<String, String>> handleMultipartException(MultipartException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "File upload failed");
        error.put("message", "Please ensure you are uploading a valid CSV file.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Catch generic processing errors (math errors, empty files)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Processing Error");
        error.put("message", "The engine couldn't analyze this file. Check your data format.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}