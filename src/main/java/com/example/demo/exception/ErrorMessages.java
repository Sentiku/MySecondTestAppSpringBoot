package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {
    EMPTY(""),
    VALIDATION("Ошибка валидации"),
    UNSUPPORTED("Неподдерживаемая ошибка"),
    UNKNOWN("Произошла непредвиденная ошибка"),
    RANGE("Параметры вне диапазона");

    private final String description;

    ErrorMessages(String description) {
        this.description = description;
    }

    @JsonValue
    public String getName(){
        return description;
    }
}
