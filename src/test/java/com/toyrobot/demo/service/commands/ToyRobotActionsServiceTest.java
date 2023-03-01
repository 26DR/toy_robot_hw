package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.domain.ToyRobot;
import com.toyrobot.demo.service.ToyRobotActionsService;
import com.toyrobot.demo.service.ToyRobotService;
import com.toyrobot.demo.service.enums.FacingDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ToyRobotActionsServiceTest {

    public static final int DEFAULT_X_POS = 0;
    public static final int DEFAULT_Y_POS = 0;
    public static final long ROBOT_ID = 1L;

    @InjectMocks
    private ToyRobotActionsService toyRobotActionsService;

    @Mock
    private ToyRobotService toyRobotService;

    @Test
    void testMoveRobot() {
        // Initialize a toy robot at position (0,0) facing north
        ToyRobot toyRobot = ToyRobot.builder()
                .facingDirection(FacingDirection.NORTH)
                .xPos(0)
                .yPos(0)
                .build();

        // Move the robot and assert that it is now at position (0,1)
        toyRobotActionsService.moveRobot(toyRobot);
        assertEquals(0, toyRobot.getXPos());
        assertEquals(1, toyRobot.getYPos());

        // Turn the robot to face east and move it, assert that it is now at position (1,1)
        toyRobotActionsService.turnRobotRight(toyRobot);
        toyRobotActionsService.moveRobot(toyRobot);
        assertEquals(1, toyRobot.getXPos());
        assertEquals(1, toyRobot.getYPos());

        // Turn the robot to face south and move it, assert that it is now at position (1,0)
        toyRobotActionsService.turnRobotRight(toyRobot);
        toyRobotActionsService.moveRobot(toyRobot);
        assertEquals(1, toyRobot.getXPos());
        assertEquals(0, toyRobot.getYPos());

        // Turn the robot to face west and move it, assert that it is now at position (0,0)
        toyRobotActionsService.turnRobotRight(toyRobot);
        toyRobotActionsService.moveRobot(toyRobot);
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
        toyRobotActionsService.turnRobotLeft(toyRobot);
        assertEquals(FacingDirection.WEST, toyRobot.getFacingDirection());

        // Turn the robot left again and assert that it is now facing south
        toyRobotActionsService.turnRobotLeft(toyRobot);
        assertEquals(FacingDirection.SOUTH, toyRobot.getFacingDirection());

        // Turn the robot left again and assert that it is now facing east
        toyRobotActionsService.turnRobotLeft(toyRobot);
        assertEquals(FacingDirection.EAST, toyRobot.getFacingDirection());

        // Turn the robot left again and assert that it has not turned a whole circle and is facing North again
        toyRobotActionsService.turnRobotLeft(toyRobot);
        assertEquals(FacingDirection.NORTH, toyRobot.getFacingDirection());
    }

    @Test
    void testTurnRobotRight_fullCircle() {
        // Initialize a toy robot facing north
        ToyRobot toyRobot = ToyRobot.builder()
                .facingDirection(FacingDirection.NORTH)
                .build();

        // Turn the robot right and assert that it is now facing east
        toyRobotActionsService.turnRobotRight(toyRobot);
        assertEquals(FacingDirection.EAST, toyRobot.getFacingDirection());

        // Turn the robot right again and assert that it is now facing south
        toyRobotActionsService.turnRobotRight(toyRobot);
        assertEquals(FacingDirection.SOUTH, toyRobot.getFacingDirection());

        // Turn the robot right again and assert that it is now facing west
        toyRobotActionsService.turnRobotRight(toyRobot);
        assertEquals(FacingDirection.WEST, toyRobot.getFacingDirection());

        // Turn the robot right again and assert that it has not turned a whole circle and is facing North again
        toyRobotActionsService.turnRobotRight(toyRobot);
        assertEquals(FacingDirection.NORTH, toyRobot.getFacingDirection());
    }

    @Test
    void reportRobotPosition_shouldReturnToyRobot_validId() {
        // given
        ToyRobot expectedRobot = new ToyRobot();
        expectedRobot.setId(ROBOT_ID);

        Mockito.when(toyRobotService.findById(expectedRobot.getId()))
                .thenReturn(expectedRobot);

        // when
        ToyRobot actualRobot = toyRobotActionsService.reportRobotPosition(expectedRobot.getId());

        // then
        Assertions.assertEquals(expectedRobot, actualRobot);
    }

    @Test
    void placeRobot_shouldReturnNewRobot_validInputs() {
        // when
        toyRobotActionsService.placeRobot(DEFAULT_X_POS, DEFAULT_Y_POS, FacingDirection.NORTH);

        // then
        verify(toyRobotService, times(1)).saveToyRobot(any(ToyRobot.class));
    }
}