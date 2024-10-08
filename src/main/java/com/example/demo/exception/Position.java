package com.example.demo.exception;

import lombok.Getter;
@Getter
public enum Position {
    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, true),
    MANAGER(2.8, true),
    MARKETING(1.5, false),
    DESIGNER(2.0, false);

    @Getter
    private final double positionCoefficient;
    private final boolean isManager;

    Position(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }

    public boolean isManager() {
        return isManager;
    }
}
