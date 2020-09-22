package com.project.demo.exceptions;

public class DocumentDeletionException extends Exception {
    private long documentId;
    private long protocolId;

    public DocumentDeletionException(long documentId, long protocolId) {
        super("Document with id {0} cannot be deleted, because exists assigned protocol with id {1}");
        this.documentId = documentId;
        this.protocolId = protocolId;
    }

    public long getDocumentId() {
        return documentId;
    }

    public long getProtocolId() {
        return protocolId;
    }
}
