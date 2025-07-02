package com.example.education.dto.response.base;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder

public class ErrorResponse<T> extends BaseResponse<T> {
    public ErrorResponse(String message, Integer statusCode) {
        super(message,statusCode);
    }
}
