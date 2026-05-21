package com.hotelgo.common.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseResponseTest {
    
    @Test
    void testSuccessResponse() {
        String data = "test data";
        BaseResponse<String> response = BaseResponse.success(data);
        
        assertEquals(200, response.getStatus());
        assertEquals("Success", response.getMessage());
        assertEquals(data, response.getData());
        assertTrue(response.getTimestamp() > 0);
    }
    
    @Test
    void testSuccessResponseWithMessage() {
        String data = "test data";
        String message = "Custom success message";
        BaseResponse<String> response = BaseResponse.success(message, data);
        
        assertEquals(200, response.getStatus());
        assertEquals(message, response.getMessage());
        assertEquals(data, response.getData());
        assertTrue(response.getTimestamp() > 0);
    }
    
    @Test
    void testErrorResponseWithStatus() {
        int status = 404;
        String message = "Not found";
        BaseResponse<Object> response = BaseResponse.error(status, message);
        
        assertEquals(status, response.getStatus());
        assertEquals(message, response.getMessage());
        assertNull(response.getData());
        assertTrue(response.getTimestamp() > 0);
    }
    
    @Test
    void testErrorResponseDefault() {
        String message = "Internal error";
        BaseResponse<Object> response = BaseResponse.error(message);
        
        assertEquals(500, response.getStatus());
        assertEquals(message, response.getMessage());
        assertNull(response.getData());
        assertTrue(response.getTimestamp() > 0);
    }
    
    @Test
    void testTimestampIsRecent() {
        BaseResponse<String> response = BaseResponse.success("test");
        long now = System.currentTimeMillis();
        
        // Timestamp should be within 1 second of now
        assertTrue(Math.abs(now - response.getTimestamp()) < 1000);
    }
}
