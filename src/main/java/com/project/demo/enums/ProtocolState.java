package com.project.demo.enums;

import java.util.Arrays;

public enum ProtocolState {
    NEW, PREPARE_FOR_SHIPMENT, CANCELED;

    public static ProtocolState parseFromCode(String state) {
        return Arrays.stream(values()).filter(value -> value.name().equals(state)).findAny().orElse(null);
    }
}
