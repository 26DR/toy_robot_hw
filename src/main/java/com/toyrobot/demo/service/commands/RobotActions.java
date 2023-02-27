package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.service.RobotControl;
import com.toyrobot.demo.service.enums.FacingDirection;
import org.springframework.stereotype.Service;

@Service
class RobotActions {

    boolean moveRobot(ToyRobot toyRobot) {
        RobotControl.moveRobot(toyRobot);
        return true;
    }

    boolean turnRobotLeft(ToyRobot toyRobot) {
        int currentFacingDirectionValue = retrieveFacingDirectionValue(toyRobot);
        int numberOfFacingDirectionValues = FacingDirection.values().length;

        boolean isCurrentDirectionFirstInCircle = currentFacingDirectionValue == 1;

        if (isCurrentDirectionFirstInCircle) {
            //Reset the direction to the last one in the circle due to the backwards motion
            toyRobot.setFacingDirection(FacingDirection.getDirectionValueByIndex(numberOfFacingDirectionValues));
        } else {
            toyRobot.setFacingDirection(FacingDirection.getDirectionValueByIndex(currentFacingDirectionValue - 1));
        }
        return true;
    }

    boolean turnRobotRight(ToyRobot toyRobot) {
        int currentFacingDirectionValue = retrieveFacingDirectionValue(toyRobot);
        int numberOfFacingDirectionValues = FacingDirection.values().length;

        boolean isCurrentDirectionLastInCircle = currentFacingDirectionValue == numberOfFacingDirectionValues;

        if (isCurrentDirectionLastInCircle) {
            //Reset the direction to the first one in the circle
            toyRobot.setFacingDirection(FacingDirection.NORTH);
        } else {
            toyRobot.setFacingDirection(FacingDirection.getDirectionValueByIndex(currentFacingDirectionValue + 1));
        }
        return true;
    }

    private static int retrieveFacingDirectionValue(ToyRobot toyRobot) {
        return toyRobot.getFacingDirection().getDirectionValue();
    }

}
