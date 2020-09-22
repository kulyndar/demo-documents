package com.project.demo.controller;

import com.project.demo.controller.resources.DocumentResource;
import com.project.demo.controller.resources.MessageResource;
import com.project.demo.exceptions.DocumentDeletionException;
import com.project.demo.exceptions.ItemNotFoundException;
import com.project.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping(value = "/{documentId}")
    public ResponseEntity<DocumentResource> getDocument(@PathVariable(value = "documentId") long documentId) throws ItemNotFoundException {
        return ResponseEntity.ok(documentService.findDocument(documentId));
    }

    @PostMapping
    public ResponseEntity<MessageResource> createDocument(@RequestBody DocumentResource documentResource) {
        long newDocumentId = documentService.createDocument(documentResource).getId();
        return ResponseEntity.ok(new MessageResource("New document with id {0} was successfully created!").withParams(newDocumentId));
    }

    @PutMapping(value = "/{documentId}")
    public ResponseEntity<MessageResource> updateDocument(@PathVariable(value = "documentId") long documentId, @RequestBody DocumentResource documentResource) throws ItemNotFoundException {
        documentService.updateDocument(documentId, documentResource);
        return ResponseEntity.ok(new MessageResource("Document with id {0} was successfully updated!").withParams(documentId));
    }


    @DeleteMapping(value = "/{documentId}")
    public ResponseEntity<MessageResource> deleteDocument(@PathVariable(value = "documentId") long documentId) throws DocumentDeletionException, ItemNotFoundException {
        documentService.deleteDocument(documentId);
        return ResponseEntity.ok(new MessageResource("Document with id {0} was successfully deleted!").withParams(documentId));
    }
}
