package com.hubartech.personalnotebookcrud.dto.responses;

import com.hubartech.personalnotebookcrud.dto.BaseDto;
import com.hubartech.personalnotebookcrud.model.NotebookModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotebookResponse extends BaseDto {
    private String noteName;
    private String content;

    public NotebookResponse(NotebookModel notebookModel) {
        super(notebookModel.getId(), notebookModel.getCreatedOn(), notebookModel.getUpdatedOn());
        this.noteName = notebookModel.getNoteName();
        this.content = notebookModel.getContent();
    }
}
