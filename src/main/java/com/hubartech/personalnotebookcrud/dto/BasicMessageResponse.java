package com.hubartech.personalnotebookcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicMessageResponse {
    int statusCode;
    String message;
}
