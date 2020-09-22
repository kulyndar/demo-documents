package com.project.demo.service;

import com.project.demo.controller.resources.DocumentResource;
import com.project.demo.exceptions.DocumentDeletionException;
import com.project.demo.exceptions.ItemNotFoundException;
import com.project.demo.model.Document;
import com.project.demo.model.Protocol;
import com.project.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Transactional
    public Document createDocument(DocumentResource documentResource) {
        validateNewDocument(documentResource);
        Document document = new Document(documentResource.getName(), documentResource.getType());
        return documentRepository.save(document);
    }

    @Transactional
    public DocumentResource findDocument(long documentId) throws ItemNotFoundException {
        Document document = getDocument(documentId);
        return DocumentResource.build(document);
    }

    @Transactional
    public void updateDocument(long documentId, DocumentResource documentResource) throws ItemNotFoundException {
        Document document = getDocument(documentId);
        validateDocument(documentResource);
        document.setName(documentResource.getName());
        document.setType(documentResource.getType());
        documentRepository.save(document);
    }

    @Transactional
    public void deleteDocument(long documentId) throws ItemNotFoundException, DocumentDeletionException {
        Document document = getDocument(documentId);
        validateDeletion(document);
        document.getProtocols().stream().forEach(p -> p.getDocuments().remove(document));
        documentRepository.delete(document);
    }

    @Transactional
    public Document getDocument(long documentId) throws ItemNotFoundException {
        return documentRepository.findById(documentId).orElseThrow(() -> ItemNotFoundException.build(ItemNotFoundException.Type.DOCUMENT, documentId));
    }
    /*---------------------------------------------------------------------------------------*/
    /*--------------------------------PRIVATE METHODS----------------------------------------*/
    /*---------------------------------------------------------------------------------------*/


    private void validateDeletion(Document document) throws DocumentDeletionException {
        if (document.getProtocols() != null && !document.getProtocols().isEmpty()) {
            Long protocolWithLastDocumentId = document.getProtocols().stream().filter(p -> p.getDocuments().size() == 1).map(Protocol::getId).findAny().orElse(null);
            if (protocolWithLastDocumentId != null) {
                throw new DocumentDeletionException(document.getId(), protocolWithLastDocumentId);
            }
        }
    }

    private void validateNewDocument(DocumentResource documentResource) {
        Assert.isNull(documentResource.getId(), "New document id must be empty");
        validateDocument(documentResource);
    }

    private void validateDocument(DocumentResource documentResource) {
        Assert.notNull(documentResource.getName(), "Name is mandatory field");
        Assert.notNull(documentResource.getType(), "Type is mandatory field");
    }
}
