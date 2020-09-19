package com.project.demo.controller;

import com.project.demo.controller.resources.MessageResource;
import com.project.demo.controller.resources.ProtocolResource;
import com.project.demo.service.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/protocols/protocol")
public class ProtocolController {

    @Autowired
    private ProtocolService protocolService;

    @GetMapping(value = "/{protocolId}")
    public ResponseEntity<ProtocolResource> getProtocol(@PathVariable(value = "protocolId") long protocolId) {

    }

    @PostMapping
    public ResponseEntity<MessageResource> createProtocol(ProtocolResource protocolResource) {

    }

    @PutMapping(value = "/{protocolId}")
    public ResponseEntity<MessageResource> updateProtocol(@PathVariable(value = "protocolId") long protocolId, ProtocolResource protocolResource) {

    }

    @PutMapping(value = "/protocolId/state")
    public ResponseEntity<MessageResource> updateProtocolState(@PathVariable(value = "protocolId") long protocolId, String state) {

    }
}
