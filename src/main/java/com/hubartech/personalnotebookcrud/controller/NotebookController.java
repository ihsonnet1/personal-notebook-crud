package com.hubartech.personalnotebookcrud.controller;

import com.hubartech.personalnotebookcrud.dto.BasicDataResponse;
import com.hubartech.personalnotebookcrud.dto.BasicMessageResponse;
import com.hubartech.personalnotebookcrud.dto.requests.NotebookRequest;
import com.hubartech.personalnotebookcrud.dto.responses.NotebookResponse;
import com.hubartech.personalnotebookcrud.service.NotebookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notebooks")
public class NotebookController {

    private final NotebookService notebookService;

    @PostMapping
    public ResponseEntity<BasicMessageResponse> createNote(@RequestBody NotebookRequest notebookRequest) {
        return notebookService.createNote(notebookRequest);
    }

    @GetMapping
    public ResponseEntity<BasicDataResponse<List<NotebookResponse>>> getAllNotes() {
        return notebookService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicDataResponse<NotebookResponse>> getNote(@PathVariable("id") Long id) {
        return notebookService.getNote(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasicDataResponse<NotebookResponse>> updateNote(@PathVariable("id") Long id, @RequestBody NotebookRequest notebookRequest) {
        return notebookService.updateNote(id, notebookRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasicMessageResponse> deleteNote(@PathVariable("id") Long id) {
        return notebookService.deleteNote(id);
    }
}
