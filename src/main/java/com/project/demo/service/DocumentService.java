package com.project.demo.service;

import com.project.demo.controller.resources.DocumentResource;
import com.project.demo.exceptions.ItemNotFoundException;
import com.project.demo.model.Document;
import com.project.demo.repository.DocumentRepository;
import com.project.demo.service.transformer.DocumentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class DocumentService {

    @Autowired
    private DocumentTransformer transformer;

    @Autowired
    private DocumentRepository documentRepository;

    public long createDocument(DocumentResource documentResource) {
        validateNewDocument(documentResource);
        Document document = new Document();
        return documentRepository.save(document).getId();
    }

    private void validateNewDocument(DocumentResource documentResource) {
        Assert.isNull(documentResource.getId(), "New document id must be empty");
        Assert.notNull(documentResource.getName(), "Name is mandatory field");
        Assert.notNull(documentResource.getType(), "Type is mandatory field");
    }

    public DocumentResource findDocument(long documentId) throws ItemNotFoundException {
        Document document = documentRepository.findById(documentId).orElseThrow(() -> ItemNotFoundException.build(ItemNotFoundException.Type.DOCUMENT, documentId));
        return DocumentResource.build(document);
    }
}
