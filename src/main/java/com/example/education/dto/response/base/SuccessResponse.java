package com.example.education.dto.response.base;

import com.example.education.enums.ResponseCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@Data
public class SuccessResponse<T> extends BaseResponse<T> {
    private T data;


    public static <R> SuccessResponse<R> createSuccessResponse(R data, ResponseCode responseCode) {
        return SuccessResponse
                .<R>builder()
                .statusCode(responseCode.getStatusCode())
                .message(responseCode.getMessage())
                .data(data)
                .build();

    }
}
