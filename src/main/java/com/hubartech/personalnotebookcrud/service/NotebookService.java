package com.hubartech.personalnotebookcrud.service;

import com.hubartech.personalnotebookcrud.dto.BasicDataResponse;
import com.hubartech.personalnotebookcrud.dto.BasicMessageResponse;
import com.hubartech.personalnotebookcrud.dto.requests.NotebookRequest;
import com.hubartech.personalnotebookcrud.dto.responses.NotebookResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotebookService {
    ResponseEntity<BasicMessageResponse> createNote(NotebookRequest notebookResponse);

    ResponseEntity<BasicDataResponse<List<NotebookResponse>>> getAllNotes();


    ResponseEntity<BasicDataResponse<NotebookResponse>> getNote(Long id);

    ResponseEntity<BasicDataResponse<NotebookResponse>> updateNote(Long id, NotebookRequest notebookRequest);

    ResponseEntity<BasicMessageResponse> deleteNote(Long id);
}
