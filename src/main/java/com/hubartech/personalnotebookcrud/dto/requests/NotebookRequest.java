package com.hubartech.personalnotebookcrud.dto.requests;

import com.hubartech.personalnotebookcrud.dto.RequestValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotebookRequest implements RequestValidator {
    private String noteName;
    private String content;

    @Override
    public boolean validate() {
        if (noteName == null || content == null) return false;
        else return true;
    }
}
