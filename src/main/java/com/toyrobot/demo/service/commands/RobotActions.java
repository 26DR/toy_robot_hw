package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.service.enums.FacingDirection;
import org.springframework.stereotype.Service;

@Service
class RobotActions {

    boolean turnRobotLeft(ToyRobot toyRobot) {
        int currentFacingDirectionValue = toyRobot.getFacingDirection().getDirectionValue();
        int numberOfFacingDirectionValues = FacingDirection.values().length;

        boolean isCurrentDirectionFirstInCircle = currentFacingDirectionValue == 1;

        if (isCurrentDirectionFirstInCircle) {
            //Reset the direction to the last one in the circle due to the backwards motion
            toyRobot.setFacingDirection(FacingDirection.getDirectionValueByIndex(numberOfFacingDirectionValues));
            return true;
        } else {
            toyRobot.setFacingDirection(FacingDirection.getDirectionValueByIndex(currentFacingDirectionValue - 1));
            return true;
        }
    }

    boolean turnRobotRight(ToyRobot toyRobot) {
        int currentFacingDirectionValue = toyRobot.getFacingDirection().getDirectionValue();
        int numberOfFacingDirectionValues = FacingDirection.values().length;

        boolean isCurrentDirectionLastInCircle = currentFacingDirectionValue == numberOfFacingDirectionValues;

        if (isCurrentDirectionLastInCircle) {
            //Reset the direction to the first one in the circle
            toyRobot.setFacingDirection(FacingDirection.NORTH);
            return true;
        } else {
            toyRobot.setFacingDirection(FacingDirection.getDirectionValueByIndex(currentFacingDirectionValue + 1));
            return true;
        }
    }

    String robotReport(ToyRobot toyRobot) {
        return "ToyRobot{" +
                "xPos=" + toyRobot.getXPos() +
                ", yPos=" + toyRobot.getYPos() +
                ", facingDirection=" + toyRobot.getFacingDirection() +
                "("
                + toyRobot.getFacingDirection().getDirectionValue() +
                ")" +
                '}';
    }
}
