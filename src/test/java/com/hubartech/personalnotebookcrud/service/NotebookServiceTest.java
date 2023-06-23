package com.hubartech.personalnotebookcrud.service;

import com.hubartech.personalnotebookcrud.dto.BasicDataResponse;
import com.hubartech.personalnotebookcrud.dto.BasicMessageResponse;
import com.hubartech.personalnotebookcrud.dto.requests.NotebookRequest;
import com.hubartech.personalnotebookcrud.dto.responses.NotebookResponse;
import com.hubartech.personalnotebookcrud.model.NotebookModel;
import com.hubartech.personalnotebookcrud.repository.NotebookRepository;
import com.hubartech.personalnotebookcrud.service.implementations.NotebookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotebookServiceTest {

    @Mock
    private NotebookRepository notebookRepository;
    private NotebookService underTest;

    @BeforeEach
    void setUp() {
        underTest = new NotebookServiceImpl(notebookRepository);
    }


    @Test
    void canCreateNote() {
        underTest.createNote(new NotebookRequest("Valid Name","Valid Content"));

        verify(notebookRepository).save(NotebookModel.builder().noteName("Valid Name").content("Valid Content").build());
    }

    @Test
    void canGetAllNotes() {
        underTest.getAllNotes();
        verify(notebookRepository).findAll();
    }

    @Test
    void getNote() {
        underTest.getNote(5l);
        verify(notebookRepository).findById(5l);
    }

    @Test
    @Disabled
    void updateNote() {
    }

    @Test
    @Disabled
    void deleteNote() {
    }
}