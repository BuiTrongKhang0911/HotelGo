package com.hotelgo.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorCodeTest {
    
    @Test
    void testErrorCodeValues() {
        assertEquals(500, ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        assertEquals("System is busy, please try again later", 
                     ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        
        assertEquals(401, ErrorCode.UNAUTHENTICATED.getCode());
        assertEquals(403, ErrorCode.UNAUTHORIZED.getCode());
        assertEquals(409, ErrorCode.USER_EXISTED.getCode());
        assertEquals(404, ErrorCode.USER_NOT_FOUND.getCode());
    }
    
    @Test
    void testAllErrorCodesHaveValidHttpStatus() {
        for (ErrorCode errorCode : ErrorCode.values()) {
            int code = errorCode.getCode();
            assertTrue(code >= 400 && code < 600, 
                      "Error code should be valid HTTP status: " + errorCode.name());
        }
    }
    
    @Test
    void testAllErrorCodesHaveMessage() {
        for (ErrorCode errorCode : ErrorCode.values()) {
            assertNotNull(errorCode.getMessage());
            assertFalse(errorCode.getMessage().isEmpty());
        }
    }
}
