package com.hotelgo.common.exception;

import com.hotelgo.common.dto.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    // 1. Handle Business Logic Exceptions (AppException)
    @ExceptionHandler(AppException.class)
    public ResponseEntity<BaseResponse<Object>> handleAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        BaseResponse<Object> response = BaseResponse.error(
                errorCode.getCode(),
                errorCode.getMessage()
        );
        
        // Log business exceptions at INFO level
        log.info("Business exception: {} - {}", errorCode.name(), errorCode.getMessage());
        
        // Return with proper HTTP Status Code
        return ResponseEntity.status(errorCode.getCode()).body(response);
    }
    
    // 2. Handle Validation Exceptions (from @Valid annotation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleValidationException(
            MethodArgumentNotValidException ex) {
        
        // Collect all validation error messages
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        BaseResponse<Object> response = BaseResponse.error(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid input data: " + errorMessage
        );
        
        log.warn("Validation error: {}", errorMessage);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    // 3. Final fallback: Handle all unhandled System Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGlobalException(Exception ex) {
        // Log detailed error for developers
        log.error("Unhandled Exception: ", ex);
        
        // Return generic message to frontend (hide sensitive details)
        BaseResponse<Object> response = BaseResponse.error(
                ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(),
                ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage()
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
