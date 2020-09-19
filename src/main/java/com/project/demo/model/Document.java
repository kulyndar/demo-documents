package com.project.demo.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDate created;

    @Column(name = "TYPE")
    private String type;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "documents")
    private Set<Protocol> protocol;

    public Document() {
    }

    public Document(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDate getCreated() {
        return created;
    }

    public String getType() {
        return type;
    }

    public Set<Protocol> getProtocol() {
        return protocol;
    }
}
