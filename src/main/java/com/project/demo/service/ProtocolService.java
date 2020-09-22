package com.project.demo.service;

import com.project.demo.controller.resources.ProtocolResource;
import com.project.demo.enums.ProtocolState;
import com.project.demo.exceptions.ItemNotFoundException;
import com.project.demo.model.Protocol;
import com.project.demo.repository.ProtocolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProtocolService {

    @Autowired
    private ProtocolRepository protocolRepository;

    @Autowired
    private DocumentService documentService;

    @Transactional
    public ProtocolResource findProtocol(long protocolId) throws ItemNotFoundException {
        Protocol protocol = protocolRepository.findById(protocolId).orElseThrow(() -> ItemNotFoundException.build(ItemNotFoundException.Type.PROTOCOL, protocolId));
        return ProtocolResource.build(protocol);
    }

    @Transactional
    public long createProtocol(ProtocolResource protocolResource) throws ItemNotFoundException {
        validateNewProtocol(protocolResource);
        Protocol protocol = new Protocol(ProtocolState.valueOf(protocolResource.getState()));
        assignDocuments(protocol, protocolResource);
        return protocolRepository.save(protocol).getId();
    }

    @Transactional
    public void updateProtocolState(long protocolId, String state) {
        validateState(state);
        Protocol protocol = protocolRepository.findById(protocolId).orElseThrow(() -> ItemNotFoundException.build(ItemNotFoundException.Type.PROTOCOL, protocolId));
        protocol.setState(ProtocolState.valueOf(state));
        protocolRepository.save(protocol);
    }

    @Transactional
    public void updateProtocol(long protocolId, ProtocolResource protocolResource) {
        validateProtocol(protocolResource);
        Protocol protocol = protocolRepository.findById(protocolId).orElseThrow(() -> ItemNotFoundException.build(ItemNotFoundException.Type.PROTOCOL, protocolId));
        protocol.setState(ProtocolState.parseFromCode(protocolResource.getState()));
        protocol.getDocuments().clear();
        assignDocuments(protocol, protocolResource);
        protocolRepository.save(protocol);
    }

    /*---------------------------------------------------------------------------------------*/
    /*--------------------------------PRIVATE METHODS----------------------------------------*/
    /*---------------------------------------------------------------------------------------*/

    private void validateProtocol(ProtocolResource protocolResource) {
        validateState(protocolResource.getState());
        Assert.isTrue((protocolResource.getDocumentIds() != null && protocolResource.getDocumentIds().stream().anyMatch(Objects::nonNull))
                        || (protocolResource.getDocuments() != null && protocolResource.getDocuments().stream().anyMatch(Objects::nonNull)),
                "Protocol has to contain at least one document");
    }

    private void validateState(String state) {
        Assert.notNull(state, "State is mandatory field");
        Assert.notNull(ProtocolState.parseFromCode(state), "Unknown state of protocol");
    }

    private void validateNewProtocol(ProtocolResource protocolResource) {
        Assert.isNull(protocolResource.getId(), "New protocol id must be empty");
        validateProtocol(protocolResource);
    }

    private void assignDocuments(Protocol protocol, ProtocolResource protocolResource) {
        if (protocolResource.getDocumentIds() != null) {
            protocol.getDocuments().addAll(protocolResource.getDocumentIds().stream().map(id -> documentService.getDocument(id)).collect(Collectors.toSet()));
        }
        if (protocolResource.getDocuments() != null) {
            protocol.getDocuments().addAll(protocolResource.getDocuments().stream().map(item -> documentService.createDocument(item)).collect(Collectors.toSet()));
        }
    }
}
