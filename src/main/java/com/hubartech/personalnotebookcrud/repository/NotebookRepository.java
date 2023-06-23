package com.hubartech.personalnotebookcrud.repository;

import com.hubartech.personalnotebookcrud.model.NotebookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface NotebookRepository extends JpaRepository<NotebookModel, Long> {

}
