package com.hotelgo.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 1. Common System Errors
    UNCATEGORIZED_EXCEPTION(500, "System is busy, please try again later"),
    INVALID_KEY(400, "Invalid request"),
    UNAUTHENTICATED(401, "Session is invalid or expired"),
    UNAUTHORIZED(403, "You do not have permission to access this resource"),
    
    // 2. Business Logic Errors
    USER_EXISTED(409, "Username or Email already exists in the system"),
    USER_NOT_FOUND(404, "User not found"),
    INVALID_CREDENTIALS(401, "Invalid username or password"),
    HOTEL_NOT_FOUND(404, "Hotel not found"),
    ROOM_NOT_AVAILABLE(400, "Room is already booked for this time period"),
    
    // 3. Token Errors
    TOKEN_EXPIRED(401, "Token has expired"),
    TOKEN_INVALID(401, "Invalid token"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh token not found"),
    REFRESH_TOKEN_EXPIRED(401, "Refresh token has expired");
    
    private final int code;
    private final String message;
    
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
