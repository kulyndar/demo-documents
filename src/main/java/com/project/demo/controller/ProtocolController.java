package com.project.demo.controller;

import com.project.demo.controller.resources.MessageResource;
import com.project.demo.controller.resources.ProtocolResource;
import com.project.demo.exceptions.ItemNotFoundException;
import com.project.demo.service.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/protocols/protocol")
public class ProtocolController {

    @Autowired
    private ProtocolService protocolService;

    @GetMapping(value = "/{protocolId}")
    public ResponseEntity<ProtocolResource> getProtocol(@PathVariable(value = "protocolId") long protocolId) throws ItemNotFoundException {
        return ResponseEntity.ok(protocolService.findProtocol(protocolId));
    }

    @PostMapping
    public ResponseEntity<MessageResource> createProtocol(@RequestBody ProtocolResource protocolResource) {
        long protocolId = protocolService.createProtocol(protocolResource);
        return ResponseEntity.ok(new MessageResource("Protocol with  id {0} was successfully created!").withParams(protocolId));
    }

    @PreAuthorize("hasRole('ROLE_HEAD_OF_PROTOCOLS')")
    @PutMapping(value = "/{protocolId}")
    public ResponseEntity<MessageResource> updateProtocol(@PathVariable(value = "protocolId") long protocolId, @RequestBody ProtocolResource protocolResource) {
        protocolService.updateProtocol(protocolId, protocolResource);
        return ResponseEntity.ok(new MessageResource("Protocol with  id {0} was successfully updated!").withParams(protocolId));
    }

    @PutMapping(value = "/{protocolId}/state")
    public ResponseEntity<MessageResource> updateProtocolState(@PathVariable(value = "protocolId") long protocolId, @RequestBody String state) {
        protocolService.updateProtocolState(protocolId, state);
        return ResponseEntity.ok(new MessageResource("Protocol state with  id {0} was successfully updated!").withParams(protocolId));
    }
}
