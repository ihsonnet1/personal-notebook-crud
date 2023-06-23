package com.hubartech.personalnotebookcrud.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotebookRequest {
    private String noteName;
    private String content;
}
