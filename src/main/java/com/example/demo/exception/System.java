package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonValue;

public enum System {
    ERP ("ERP"),
    CRM ("CRM"),
    WMS ("WMS");
    private final String name;
    System(String name) { this.name = name; }
    @JsonValue
    public String getName() { return name; }
    @Override
    public String toString() { return name; }
}
