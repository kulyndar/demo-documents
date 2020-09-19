package com.project.demo.model;

import com.project.demo.enums.ProtocolState;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Protocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private ProtocolState state;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDate created;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PROTOCOL_DOCUMENT", joinColumns = {@JoinColumn(name = "PROTOCOL_ID")}, inverseJoinColumns = {@JoinColumn(name = "DOCUMENT_ID")})
    private Set<Document> documents;
}
