package com.hotelgo.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppExceptionTest {
    
    @Test
    void testAppExceptionCreation() {
        AppException exception = new AppException(ErrorCode.USER_NOT_FOUND);
        
        assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
        assertEquals(ErrorCode.USER_NOT_FOUND.getMessage(), exception.getMessage());
    }
    
    @Test
    void testAppExceptionIsRuntimeException() {
        AppException exception = new AppException(ErrorCode.USER_EXISTED);
        
        assertTrue(exception instanceof RuntimeException);
    }
    
    @Test
    void testDifferentErrorCodes() {
        AppException exception1 = new AppException(ErrorCode.USER_NOT_FOUND);
        AppException exception2 = new AppException(ErrorCode.HOTEL_NOT_FOUND);
        
        assertNotEquals(exception1.getErrorCode(), exception2.getErrorCode());
        assertNotEquals(exception1.getMessage(), exception2.getMessage());
    }
}
