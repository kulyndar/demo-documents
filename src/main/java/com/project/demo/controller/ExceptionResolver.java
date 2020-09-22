package com.project.demo.controller;

import com.project.demo.controller.resources.MessageResource;
import com.project.demo.exceptions.DocumentDeletionException;
import com.project.demo.exceptions.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResource> handle(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new MessageResource(ex.getMessage()));
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<MessageResource> handle(ItemNotFoundException ex) {
        return ResponseEntity.badRequest().body(new MessageResource(ex.getMessage()).withParams(ex.getItemId()));
    }

    @ExceptionHandler(DocumentDeletionException.class)
    public ResponseEntity<MessageResource> handle(DocumentDeletionException ex) {
        return ResponseEntity.badRequest().body(new MessageResource(ex.getMessage()).withParams(ex.getDocumentId(), ex.getProtocolId()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResource> handle(Exception ex) {
        return ResponseEntity.badRequest().body(new MessageResource(ex.getMessage()));
    }
}
