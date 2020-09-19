package com.project.demo.controller.resources;

import com.project.demo.model.Document;

import java.time.LocalDate;

public class DocumentResource {
    private Long id;
    private String name;
    private String type;
    private LocalDate created;
    private String createdBy;

    public DocumentResource() {
    }

    public static DocumentResource build(Document document) {
        DocumentResource resource = new DocumentResource();
        resource.id = document.getId();
        resource.name = document.getName();
        resource.type = document.getType();
        resource.created = document.getCreated();
        resource.createdBy = document.getCreatedBy();
        return resource;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LocalDate getCreated() {
        return created;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
