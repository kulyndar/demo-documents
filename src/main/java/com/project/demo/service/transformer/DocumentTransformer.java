package com.project.demo.service.transformer;

import com.project.demo.controller.resources.DocumentResource;
import com.project.demo.model.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentTransformer {
    public Document getDocumentFromResource(DocumentResource documentResource) {
        Document document = new Document();
        return getDocumentFromResource(document, documentResource);
    }

    private Document getDocumentFromResource(Document document, DocumentResource documentResource) {
        return document;
    }
}
