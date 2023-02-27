package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.service.enums.FacingDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RobotActionsTest {

    @InjectMocks
    private RobotActions robotActions;

    @Test
    void testMoveRobot() {
        // Initialize a toy robot at position (0,0) facing north
        ToyRobot toyRobot = ToyRobot.builder()
                .facingDirection(FacingDirection.NORTH)
                .xPos(0)
                .yPos(0)
                .build();

        // Move the robot and assert that it is now at position (0,1)
        robotActions.moveRobot(toyRobot);
        assertEquals(0, toyRobot.getXPos());
        assertEquals(1, toyRobot.getYPos());

        // Turn the robot to face east and move it, assert that it is now at position (1,1)
        robotActions.turnRobotRight(toyRobot);
        robotActions.moveRobot(toyRobot);
        assertEquals(1, toyRobot.getXPos());
        assertEquals(1, toyRobot.getYPos());

        // Turn the robot to face south and move it, assert that it is now at position (1,0)
        robotActions.turnRobotRight(toyRobot);
        robotActions.moveRobot(toyRobot);
        assertEquals(1, toyRobot.getXPos());
        assertEquals(0, toyRobot.getYPos());

        // Turn the robot to face west and move it, assert that it is now at position (0,0)
        robotActions.turnRobotRight(toyRobot);
        robotActions.moveRobot(toyRobot);
        assertEquals(0, toyRobot.getXPos());
        assertEquals(0, toyRobot.getYPos());
    }


    @Test
    void turnRobotLeft_fullCircle() {
        // Initialize a toy robot facing north
        ToyRobot toyRobot = ToyRobot.builder()
                .facingDirection(FacingDirection.NORTH)
                .build();

        // Turn the robot left and assert that it is now facing west
        robotActions.turnRobotLeft(toyRobot);
        assertEquals(FacingDirection.WEST, toyRobot.getFacingDirection());

        // Turn the robot left again and assert that it is now facing south
        robotActions.turnRobotLeft(toyRobot);
        assertEquals(FacingDirection.SOUTH, toyRobot.getFacingDirection());

        // Turn the robot left again and assert that it is now facing east
        robotActions.turnRobotLeft(toyRobot);
        assertEquals(FacingDirection.EAST, toyRobot.getFacingDirection());

        // Turn the robot left again and assert that it has not turned a whole circle and is facing North again
        robotActions.turnRobotLeft(toyRobot);
        assertEquals(FacingDirection.NORTH, toyRobot.getFacingDirection());
    }

    @Test
    void testTurnRobotRight_fullCircle() {
        // Initialize a toy robot facing north
        ToyRobot toyRobot = ToyRobot.builder()
                .facingDirection(FacingDirection.NORTH)
                .build();

        // Turn the robot right and assert that it is now facing east
        robotActions.turnRobotRight(toyRobot);
        assertEquals(FacingDirection.EAST, toyRobot.getFacingDirection());

        // Turn the robot right again and assert that it is now facing south
        robotActions.turnRobotRight(toyRobot);
        assertEquals(FacingDirection.SOUTH, toyRobot.getFacingDirection());

        // Turn the robot right again and assert that it is now facing west
        robotActions.turnRobotRight(toyRobot);
        assertEquals(FacingDirection.WEST, toyRobot.getFacingDirection());

        // Turn the robot right again and assert that it has not turned a whole circle and is facing North again
        robotActions.turnRobotRight(toyRobot);
        assertEquals(FacingDirection.NORTH, toyRobot.getFacingDirection());
    }
}