package com.hubartech.personalnotebookcrud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NOTEBOOKS")
public class NotebookModel extends BaseEntity{

    private String noteName;

    @Column(columnDefinition = "TEXT")
    private String content;
}
