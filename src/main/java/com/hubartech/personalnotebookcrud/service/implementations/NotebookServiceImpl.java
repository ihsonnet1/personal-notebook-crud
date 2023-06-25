package com.hubartech.personalnotebookcrud.service.implementations;

import com.hubartech.personalnotebookcrud.dto.BasicDataResponse;
import com.hubartech.personalnotebookcrud.dto.BasicMessageResponse;
import com.hubartech.personalnotebookcrud.dto.requests.NotebookRequest;
import com.hubartech.personalnotebookcrud.dto.responses.NotebookResponse;
import com.hubartech.personalnotebookcrud.model.NotebookModel;
import com.hubartech.personalnotebookcrud.repository.NotebookRepository;
import com.hubartech.personalnotebookcrud.service.NotebookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotebookServiceImpl implements NotebookService {

    private final NotebookRepository notebookRepository;
    @Override
    public ResponseEntity<BasicMessageResponse> createNote(NotebookRequest notebookRequest) {

        if (!notebookRequest.validate()) return new ResponseEntity<>(new BasicMessageResponse(400,"Invalid Input! Note Creation Failed"), HttpStatus.BAD_REQUEST);

        NotebookModel notebookModel = NotebookModel.builder()
                .noteName(notebookRequest.getNoteName())
                .content(notebookRequest.getContent())
                .build();

        try {
            notebookRepository.save(notebookModel);
            return new ResponseEntity<>(new BasicMessageResponse(201,"Note Created Successfully"), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new BasicMessageResponse(400,"Note Creation Failed"), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<BasicDataResponse<List<NotebookResponse>>> getAllNotes() {

        List<NotebookModel> notebookModels = notebookRepository.findAll();

        List<NotebookResponse> notebookResponses = new ArrayList<>();
        for (int i=0;i<notebookModels.size();i++){
            notebookResponses.add(new NotebookResponse(notebookModels.get(i)));
        }

        if (notebookResponses.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notebook is Empty");
        else return new ResponseEntity<>(new BasicDataResponse<>(200,"Notes found!", notebookResponses), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasicDataResponse<NotebookResponse>> getNote(Long id) {
        Optional<NotebookModel> notebookModel = notebookRepository.findById(id);


        if (notebookModel.isPresent()){
            NotebookResponse notebookResponse = new NotebookResponse(notebookModel.get());
            return new ResponseEntity<>(new BasicDataResponse<>(200, "Note found!", notebookResponse), HttpStatus.FOUND);
        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<BasicDataResponse<NotebookResponse>> updateNote(Long id, NotebookRequest notebookRequest) {
        Optional<NotebookModel> notebookModel = notebookRepository.findById(id);

        NotebookResponse notebookResponse = new NotebookResponse();

        if (notebookModel.isPresent()) {
            NotebookModel notebookModel1 = notebookModel.get();
            notebookModel1.setNoteName(notebookRequest.getNoteName());
            notebookModel1.setContent(notebookRequest.getContent());

            notebookRepository.save(notebookModel1);
            BeanUtils.copyProperties(notebookModel1, notebookResponse);

            return new ResponseEntity<>(new BasicDataResponse<>(200, "Note Updated Successfully", notebookResponse), HttpStatus.OK);
        }else return new ResponseEntity<>(new BasicDataResponse<>(404, "Note Not Found", null), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<BasicMessageResponse> deleteNote(Long id) {
        Optional<NotebookModel> notebookModel = notebookRepository.findById(id);

        if (notebookModel.isPresent()){
            notebookRepository.delete(notebookModel.get());
            return new ResponseEntity<>(new BasicMessageResponse(200,"Note Deleted Successfully"), HttpStatus.OK);
        }else return new ResponseEntity<>(new BasicMessageResponse(404,"Note Not Found"), HttpStatus.NOT_FOUND);
    }
}
