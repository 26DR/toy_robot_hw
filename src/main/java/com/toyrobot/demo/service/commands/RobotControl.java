package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.exception.ToyRobotException;
import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.service.enums.FacingDirection;

public abstract class RobotControl {

    private static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 4;

    public static ToyRobot placeANewRobot(int xPos, int yPos, FacingDirection facingDirection) {
        if (isPlacementNotRestricted(xPos, yPos)) {
            return ToyRobot.builder()
                    .xPos(xPos)
                    .yPos(yPos)
                    .facingDirection(facingDirection)
                    .build();
        } else {
            throw new ToyRobotException("Robot not placed due to placement restrictions");
        }
    }

    public static void moveRobot(ToyRobot toyRobot) {
        int yPos = toyRobot.getYPos();
        int xPos = toyRobot.getXPos();

        switch (toyRobot.getFacingDirection()) {
            case NORTH:
                yPos = (toyRobot.getYPos() + 1);
                break;
            case EAST:
                xPos = (toyRobot.getXPos() + 1);
                break;
            case SOUTH:
                yPos = (toyRobot.getYPos() - 1);
                break;
            case WEST:
                xPos = (toyRobot.getXPos() - 1);
        }

        if (isPlacementNotRestricted(xPos, yPos)) {
            toyRobot.setXPos(xPos);
            toyRobot.setYPos(yPos);
            return;
        }

        throw new ToyRobotException("Robot not moved due to placement restrictions");
    }

    private static boolean isPlacementNotRestricted(int xPos, int yPos) {
        return xPos >= MIN_POSITION && xPos <= MAX_POSITION && yPos >= MIN_POSITION && yPos <= MAX_POSITION;
    }
}
