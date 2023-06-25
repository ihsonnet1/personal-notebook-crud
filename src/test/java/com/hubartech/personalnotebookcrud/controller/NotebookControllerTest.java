package com.hubartech.personalnotebookcrud.controller;

import com.hubartech.personalnotebookcrud.dto.BasicDataResponse;
import com.hubartech.personalnotebookcrud.dto.BasicMessageResponse;
import com.hubartech.personalnotebookcrud.dto.requests.NotebookRequest;
import com.hubartech.personalnotebookcrud.dto.responses.NotebookResponse;
import com.hubartech.personalnotebookcrud.service.NotebookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotebookControllerTest {

    @InjectMocks
    private NotebookController notebookController;

    @Mock
    private NotebookService notebookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    //    Test Cases for CreateNote Api
    @Test
    public void shouldSuccessCreatNote() {
        // Given
        NotebookRequest request = new NotebookRequest("Test Note", "Test Content");
        BasicMessageResponse messageResponse = new BasicMessageResponse(201, "Note Created Successfully");

        // Mocking
        when(notebookService.createNote(request)).thenReturn(new ResponseEntity<>(messageResponse, HttpStatus.CREATED));

        // Actual
        ResponseEntity<BasicMessageResponse> actualResponse = notebookController.createNote(request);

        // Assertion
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals("Note Created Successfully", actualResponse.getBody().getMessage());
        verify(notebookService, times(1)).createNote(request);
    }

    @Test
    public void shouldReturn400WhenCreateNoteWithNull() {
        // Given
        NotebookRequest request = new NotebookRequest(null, null);
        BasicMessageResponse messageResponse = new BasicMessageResponse(400, "Invalid Input! Note Creation Failed");

        // Mocking
        when(notebookService.createNote(request)).thenReturn(new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST));

        // Actual
        ResponseEntity<BasicMessageResponse> actualResponse = notebookController.createNote(request);

        // Assertion
        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
        assertEquals("Invalid Input! Note Creation Failed", actualResponse.getBody().getMessage());
        verify(notebookService, times(1)).createNote(request);
    }

    @Test
    public void shouldReturnExceptionWhenCreateNote() {
        // Given
        NotebookRequest request = new NotebookRequest("Test Note", "Test Content");

        // Mocking
        when(notebookService.createNote(request)).thenThrow(new RuntimeException("Unexpected error"));

        // Act and Assertion
        assertThrows(RuntimeException.class, () -> notebookController.createNote(request));
    }


    //    Test Cases for GetAllNote Api
    @Test
    public void shouldSuccessGetAllNotes() {
        // Given
        List<NotebookResponse> expectedNotes = Arrays.asList(
                new NotebookResponse("Note 1", "Content 1"),
                new NotebookResponse("Note 2", "Content 2")
        );

        // Mocking
        when(notebookService.getAllNotes()).thenReturn(new ResponseEntity<>(new BasicDataResponse<>(expectedNotes), HttpStatus.OK));

        // Actual
        ResponseEntity<BasicDataResponse<List<NotebookResponse>>> actualResponse = notebookController.getAllNotes();

        // Assertion
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedNotes, actualResponse.getBody().getData());
    }

    @Test
    public void shouldReturn404WhenGetAllNotes() {
        // Given
        List<NotebookResponse> response = new ArrayList<>();

        // Mocking
        when(notebookService.getAllNotes()).thenReturn(new ResponseEntity<>(new BasicDataResponse<>(404,"Notebook is Empty",response), HttpStatus.NOT_FOUND));

        // Actual
        ResponseEntity<BasicDataResponse<List<NotebookResponse>>> actualResponse = notebookController.getAllNotes();

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
        assertEquals("Notebook is Empty", actualResponse.getBody().getMessage());
        assertTrue(actualResponse.getBody().getData().isEmpty());
    }


    //    Test Cases for GetNoteById Api
    @Test
    public void shouldSuccessGetNoteById() {
        // Given and Mocking
        NotebookResponse response = new NotebookResponse("Note 1", "Content 1");
        when(notebookService.getNote(1L)).thenReturn(new ResponseEntity<>(new BasicDataResponse<>(200,"Note found!",response),HttpStatus.OK));

        // Actual
        ResponseEntity<BasicDataResponse<NotebookResponse>> actualResponse = notebookController.getNote(1L);

        // Assertion
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Note found!", actualResponse.getBody().getMessage());
        assertEquals(response, actualResponse.getBody().getData());
    }

    @Test
    public void shouldReturn404WhenGetNoteById() {
        // Mocking
        when(notebookService.getNote(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        // Actual
        ResponseEntity<BasicDataResponse<NotebookResponse>> actualResponse = notebookController.getNote(1L);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    public void shouldReturnExceptionWhenGetNoteById() {
        // Given
        Long id = 1L;
        when(notebookService.getNote(id)).thenThrow(new RuntimeException("Unexpected error"));

        // Act and Assertion
        assertThrows(RuntimeException.class, () -> notebookController.getNote(id));
    }


    //    Test Cases for UpdateNote Api
    @Test
    public void shouldSuccessUpdateNote() {
        // Given
        NotebookRequest request = new NotebookRequest("Updated Note", "Updated Content");
        NotebookResponse updatedNote = new NotebookResponse("Updated Note", "Updated Content");
        // Mocking
        when(notebookService.updateNote(1L, request)).thenReturn(new ResponseEntity<>(new BasicDataResponse<>(200,"Note Updated Successfully",updatedNote), HttpStatus.OK));

        // Actual
        ResponseEntity<BasicDataResponse<NotebookResponse>> actualResponse = notebookController.updateNote(1L, request);

        // Assertion
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Note Updated Successfully", actualResponse.getBody().getMessage());
        assertEquals(updatedNote, actualResponse.getBody().getData());
    }

    @Test
    public void shouldReturn404whenUpdateNoteWithWrongId() {
        // Given
        NotebookRequest request = new NotebookRequest("Updated Note", "Updated Content");
        NotebookResponse response = new NotebookResponse();

        // Mocking
        when(notebookService.updateNote(11L,request)).thenReturn(new ResponseEntity<>(new BasicDataResponse<>(404,"Note Not Found",response),HttpStatus.NOT_FOUND));

        // Actual
        ResponseEntity<BasicDataResponse<NotebookResponse>> actualResponse = notebookController.updateNote(11L,request);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    public void shouldReturnExceptionWhenUpdateNote() {
        // Given
        Long id = 1L;
        NotebookRequest request = new NotebookRequest("Updated Note", "Updated Content");

        // Mocking
        when(notebookService.updateNote(id, request)).thenThrow(new RuntimeException("Unexpected error"));

        // Act and Assertion
        assertThrows(RuntimeException.class, () -> notebookController.updateNote(id, request));
    }


    //    Test Cases for DeleteNote Api
    @Test
    public void shouldSuccessDeleteNote() {
        // Given
        Long id = 1L;
        // Mocking
        when(notebookService.deleteNote(id)).thenReturn(new ResponseEntity<>(new BasicMessageResponse(200, "Note deleted successfully"), HttpStatus.OK));

        // Actual
        ResponseEntity<BasicMessageResponse> actualResponse = notebookController.deleteNote(id);

        // Assertion
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Note deleted successfully", actualResponse.getBody().getMessage());
    }

    @Test
    public void shouldReturn404WhenDeleteNoteWithWrongId() {
        // Given
        Long id = 1L;

        // Mocking
        when(notebookService.deleteNote(id)).thenReturn(new ResponseEntity<>(new BasicMessageResponse(404, "Note not found"), HttpStatus.NOT_FOUND));

        // Actual
        ResponseEntity<BasicMessageResponse> actualResponse = notebookController.deleteNote(id);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
        assertEquals("Note not found", actualResponse.getBody().getMessage());
    }

    @Test
    public void ShouldReturnExceptionWhenDeleteNote() {
        // Given and Mocking
        Long id = 1L;
        when(notebookService.deleteNote(id)).thenThrow(new RuntimeException("Unexpected error"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> notebookController.deleteNote(id));
    }
}