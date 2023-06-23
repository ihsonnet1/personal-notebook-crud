package com.hubartech.personalnotebookcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicDataResponse<T> extends BasicMessageResponse{
    T data;

    public BasicDataResponse(int statusCode, String message, T data) {
        super(statusCode, message);
        this.data = data;
    }
}
