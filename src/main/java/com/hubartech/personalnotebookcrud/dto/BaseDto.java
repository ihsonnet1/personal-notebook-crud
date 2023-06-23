package com.hubartech.personalnotebookcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private Long id;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
