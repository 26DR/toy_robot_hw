package com.toyrobot.demo.service;

import com.toyrobot.demo.domain.ToyRobot;
import com.toyrobot.demo.exception.ToyRobotException;
import com.toyrobot.demo.service.enums.FacingDirection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ToyRobotActionsService {

    private static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 4;

    private final ToyRobotService toyRobotService;

    public void moveRobot(ToyRobot toyRobot) {
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

    public void turnRobotLeft(ToyRobot toyRobot) {
        int currentFacingDirectionValue = retrieveFacingDirectionValue(toyRobot);
        int numberOfFacingDirectionValues = FacingDirection.values().length;

        boolean isCurrentDirectionFirstInCircle = currentFacingDirectionValue == 1;

        if (isCurrentDirectionFirstInCircle) {
            //Reset the direction to the last one in the circle due to the backwards motion
            toyRobot.setFacingDirection(FacingDirection.getDirectionValueByIndex(numberOfFacingDirectionValues));
        } else {
            toyRobot.setFacingDirection(FacingDirection.getDirectionValueByIndex(currentFacingDirectionValue - 1));
        }
    }

    public void turnRobotRight(ToyRobot toyRobot) {
        int currentFacingDirectionValue = retrieveFacingDirectionValue(toyRobot);
        int numberOfFacingDirectionValues = FacingDirection.values().length;

        boolean isCurrentDirectionLastInCircle = currentFacingDirectionValue == numberOfFacingDirectionValues;

        if (isCurrentDirectionLastInCircle) {
            //Reset the direction to the first one in the circle
            toyRobot.setFacingDirection(FacingDirection.NORTH);
        } else {
            toyRobot.setFacingDirection(FacingDirection.getDirectionValueByIndex(currentFacingDirectionValue + 1));
        }
    }

    public ToyRobot reportRobotPosition(Long id) {
        return toyRobotService.findById(id);
    }

    public Long placeRobot(int xPos, int yPos, FacingDirection facingDirection) {
        validatePosition(xPos, yPos);
        ToyRobot newRobot = ToyRobot.builder()
                .xPos(xPos)
                .yPos(yPos)
                .facingDirection(facingDirection)
                .build();
        toyRobotService.saveToyRobot(newRobot);
        return newRobot.getId();
    }

    private static int retrieveFacingDirectionValue(ToyRobot toyRobot) {
        return toyRobot.getFacingDirection().getDirectionValue();
    }

    private static void validatePosition(int xPos, int yPos) {
        if (xPos < MIN_POSITION || xPos > MAX_POSITION || yPos < MIN_POSITION || yPos > MAX_POSITION) {
            throw new ToyRobotException("Robot not placed/moved due to placement restrictions");
        }
    }
}
