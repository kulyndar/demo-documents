package com.project.demo.exceptions;

import com.project.demo.controller.resources.MessageResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResource> handle(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new MessageResource(ex.getMessage()));
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<MessageResource> handle(ItemNotFoundException ex) {
        return ResponseEntity.badRequest().body(new MessageResource(ex.getMessage()).withParams(ex.getItemId()));
    }
}
