package com.example.quanlychitieuv2.exception;

import com.example.quanlychitieuv2.dto.ApiResponseError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@ControllerAdvice

public class GlobalExceptionHandler {


    @ExceptionHandler({ResourceNotFound.class})
    public ResponseEntity<ApiResponseError<?>> handleResourceNotFound(ResourceNotFound e, HttpServletRequest request) {
        String path = request.getRequestURI();
        ApiResponseError<?> errorResponse = new ApiResponseError<>(
               e.getMessage(),
                404,
                path,
                new Date()
        );
        return ResponseEntity.status(404).body(errorResponse);
    }

}
