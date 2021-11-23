package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.exception.ToyRobotException;
import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.service.enums.FacingDirection;

public abstract class RobotPlacer {

    private static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 4;

    public static ToyRobot placeANewRobot(int xPos, int yPos, FacingDirection facingDirection) {
        boolean xPlacementNotRestricted = xPos >= MIN_POSITION && xPos <= MAX_POSITION;
        boolean yPlacementNotRestricted = yPos >= MIN_POSITION  && yPos <= MAX_POSITION;

        if (xPlacementNotRestricted && yPlacementNotRestricted) {
            return ToyRobot.builder()
                    .xPos(xPos)
                    .yPos(yPos)
                    .facingDirection(facingDirection)
                    .build();
        } else {
            throw new ToyRobotException("Robot not placed due to placement restrictions");
        }
    }
}
