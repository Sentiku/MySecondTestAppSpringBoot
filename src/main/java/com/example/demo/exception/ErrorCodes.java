package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCodes {
    EMPTY(""),
    VALIDATION_EXCEPTION("ValidationException"),
    UNKNOWN_EXCEPTION("UnknownException"),
    UNSUPPORTED_EXCEPTION("UnsupportedException"),
    OUT_OF_RANGE("OutOfRange");

    private final String name;
    ErrorCodes (String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
