package com.toyrobot.demo.service.enums;

import com.toyrobot.demo.exception.ToyRobotException;

import java.util.Arrays;

public enum FacingDirection {
    /*The Facing direction represented as NORTH 1, EAST 2, SOUTH 3, WEST 4
     * moving in a clockwise motion.*/
    NORTH(1),
    EAST(2),
    SOUTH(3),
    WEST(4);

    private final int directionValue;

    FacingDirection(int directionValue) {
        this.directionValue = directionValue;
    }

    public int getDirectionValue() {
        return this.directionValue;
    }

    /*Gets FacingDirection value depending on index*/
    public static FacingDirection getDirectionValueByIndex(int indexToFind) {
        return Arrays.stream(FacingDirection.values())
                .filter(value -> value.directionValue == indexToFind)
                .findFirst()
                .orElseThrow(() -> new ToyRobotException("Direction index associated value not found"));
    }
}
