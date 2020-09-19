package com.project.demo.exceptions;

public class ItemNotFoundException extends Exception {

    private long itemId;

    public ItemNotFoundException(String message, long itemId) {
        super(message);
        this.itemId = itemId;
    }

    public static ItemNotFoundException build(Type type, long itemId){
        switch (type){
            case DOCUMENT:
                return new ItemNotFoundException("Document with id {0} was not found", itemId);
            case PROTOCOL:
                return new ItemNotFoundException("Protocol with id {0} was not found", itemId);
            default:
                return new ItemNotFoundException("Unknown element with id {0} was not found", itemId);
        }
    }

    public long getItemId() {
        return itemId;
    }

    public enum Type {
        DOCUMENT, PROTOCOL
    }
}
