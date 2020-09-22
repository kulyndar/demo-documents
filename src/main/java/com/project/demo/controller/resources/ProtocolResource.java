package com.project.demo.controller.resources;

import com.project.demo.enums.ProtocolState;
import com.project.demo.model.Document;
import com.project.demo.model.Protocol;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class ProtocolResource {

    private Long id;
    private String state;
    private String createdBy;
    private LocalDate created;

    private Set<DocumentResource> documents;
    private Set<Long> documentIds;

    public ProtocolResource() {
    }

    public static ProtocolResource build(Protocol protocol) {
        ProtocolResource resource = new ProtocolResource();
        resource.id = protocol.getId();
        resource.state = protocol.getState().name();
        resource.created = protocol.getCreated();
        resource.createdBy = protocol.getCreatedBy();
        resource.documents = protocol.getDocuments().stream().map(DocumentResource::build).collect(Collectors.toSet());
        resource.documentIds = protocol.getDocuments().stream().map(Document::getId).collect(Collectors.toSet());
        return resource;
    }

    public Long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Set<DocumentResource> getDocuments() {
        return documents;
    }

    public Set<Long> getDocumentIds() {
        return documentIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setDocuments(Set<DocumentResource> documents) {
        this.documents = documents;
    }

    public void setDocumentIds(Set<Long> documentIds) {
        this.documentIds = documentIds;
    }
}
