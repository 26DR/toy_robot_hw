package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.exception.ToyRobotException;
import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.service.enums.FacingDirection;

public abstract class RobotControl {

    private static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 4;

    public static ToyRobot placeANewRobot(int xPos, int yPos, FacingDirection facingDirection) {
        validatePosition(xPos, yPos);
        return ToyRobot.builder()
                .xPos(xPos)
                .yPos(yPos)
                .facingDirection(facingDirection)
                .build();
    }

    public static void moveRobot(ToyRobot toyRobot) {
        int yPos = toyRobot.getYPos();
        int xPos = toyRobot.getXPos();

        switch (toyRobot.getFacingDirection()) {
            case NORTH:
                yPos++;
                break;
            case EAST:
                xPos++;
                break;
            case SOUTH:
                yPos--;
                break;
            case WEST:
                xPos--;
        }

        validatePosition(xPos, yPos);
        toyRobot.setXPos(xPos);
        toyRobot.setYPos(yPos);
    }

    private static void validatePosition(int xPos, int yPos) {
        if (xPos < MIN_POSITION || xPos > MAX_POSITION || yPos < MIN_POSITION || yPos > MAX_POSITION) {
            throw new ToyRobotException("Robot not placed/moved due to placement restrictions");
        }
    }
}
