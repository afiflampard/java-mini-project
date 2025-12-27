package com.gdsc.boilerplate.helper;

import java.time.Instant;

import org.springframework.http.ResponseEntity;

import com.gdsc.boilerplate.dto.response.ApiResponse;

public class ResponseHelper {
    
    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return ResponseEntity.ok(
                ApiResponse.<T>builder()
                        .success(true)
                        .message(message)
                        .data(data)
                        .timestamp(Instant.now())
                        .build()
        );
    }

    public static ResponseEntity<ApiResponse<Object>> error(String message) {
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .success(false)
                        .message(message)
                        .timestamp(Instant.now())
                        .build()
        );
    }

    public static ResponseEntity<ApiResponse<Object>> error(String message, Object errors) {
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .success(false)
                        .message(message)
                        .errors(errors)
                        .timestamp(Instant.now())
                        .build()
        );
    }
}
