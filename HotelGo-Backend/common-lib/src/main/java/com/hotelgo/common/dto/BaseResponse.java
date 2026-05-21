package com.hotelgo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private int status;
    private String message;
    private T data;
    private long timestamp;
    
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, "Success", data, System.currentTimeMillis());
    }
    
    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(200, message, data, System.currentTimeMillis());
    }
    
    public static <T> BaseResponse<T> error(int status, String message) {
        return new BaseResponse<>(status, message, null, System.currentTimeMillis());
    }
    
    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(500, message, null, System.currentTimeMillis());
    }
}
