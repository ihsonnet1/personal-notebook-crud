package com.hubartech.personalnotebookcrud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @CreatedDate
    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "UPDATED_ON", nullable = false)
    private LocalDateTime UpdatedOn;
}
