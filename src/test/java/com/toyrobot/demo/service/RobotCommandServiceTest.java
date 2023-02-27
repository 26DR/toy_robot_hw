package com.toyrobot.demo.service;

import com.toyrobot.demo.exception.ToyRobotException;
import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.repository.ToyRobotRepository;
import com.toyrobot.demo.service.commands.LeftCommand;
import com.toyrobot.demo.service.commands.MoveCommand;
import com.toyrobot.demo.service.commands.RightCommand;
import com.toyrobot.demo.service.enums.Commands;
import com.toyrobot.demo.service.enums.FacingDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RobotCommandServiceTest {

    public static final int DEFAULT_X_POS = 0;
    public static final int DEFAULT_Y_POS = 0;
    public static final long ROBOT_ID = 1L;
    public static final long INVALID_ROBOT_ID = 999L;
    @InjectMocks
    private RobotCommandService robotCommandService;

    @Mock
    private MoveCommand moveCommand;

    @Mock
    private LeftCommand leftCommand;

    @Mock
    private RightCommand rightCommand;

    @Mock
    private ToyRobotRepository repository;


    @Test
    void reportRobotPosition_shouldReturnToyRobot_validId() {
        // given
        ToyRobot expectedRobot = new ToyRobot();
        expectedRobot.setId(ROBOT_ID);

        Mockito.when(repository.findById(expectedRobot.getId()))
                .thenReturn(Optional.of(expectedRobot));

        // when
        ToyRobot actualRobot = robotCommandService.reportRobotPosition(expectedRobot.getId());

        // then
        Assertions.assertEquals(expectedRobot, actualRobot);
    }

    @Test
    void reportRobotPosition_shouldThrowToyRobotException_invalidId() {
        Mockito.when(repository.findById(INVALID_ROBOT_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ToyRobotException.class, () -> robotCommandService.reportRobotPosition(INVALID_ROBOT_ID));
    }

    @Test
    void placeRobot_shouldReturnNewRobot_validInputs() {
        // given
        ToyRobot expectedRobot = ToyRobot.builder()
                .xPos(DEFAULT_X_POS)
                .yPos(DEFAULT_Y_POS)
                .facingDirection(FacingDirection.NORTH)
                .build();

        // when
        ToyRobot actualRobot = robotCommandService.placeRobot(DEFAULT_X_POS, DEFAULT_Y_POS, FacingDirection.NORTH);

        // then
        verify(repository, times(1)).save(any(ToyRobot.class));
        Assertions.assertEquals(expectedRobot.getXPos(), actualRobot.getXPos());
        Assertions.assertEquals(expectedRobot.getYPos(), actualRobot.getYPos());
        Assertions.assertEquals(expectedRobot.getFacingDirection(), actualRobot.getFacingDirection());
    }

    @ParameterizedTest(name = "{index} => command=''{0}''")
    @EnumSource(Commands.class)
    void controlRobot_shouldInvokeSave_whenAnyCommandUsed(Commands command) {
        // given
        ToyRobot toyRobot = ToyRobot.builder()
                .id(ROBOT_ID)
                .build();

        Mockito.when(repository.findById(ROBOT_ID))
                .thenReturn(Optional.of(toyRobot));

        // when
        robotCommandService.controlRobot(ROBOT_ID, command);

        // then
        verify(repository, times(1))
                .save(toyRobot);
    }

    @Test
    void controlRobot_shouldInvokeMoveCommand_whenCommandIsMove() {
        // given
        ToyRobot toyRobot = ToyRobot.builder()
                .id(ROBOT_ID)
                .build();

        Mockito.when(repository.findById(ROBOT_ID))
                .thenReturn(Optional.of(toyRobot));

        // when
        robotCommandService.controlRobot(ROBOT_ID, Commands.MOVE);

        // then
        verify(moveCommand, times(1))
                .executeCommand(toyRobot);
    }

    @Test
    void controlRobot_shouldInvokeMoveCommand_whenCommandIsLeft() {
        // given
        ToyRobot toyRobot = ToyRobot.builder()
                .id(ROBOT_ID)
                .build();

        Mockito.when(repository.findById(ROBOT_ID))
                .thenReturn(Optional.of(toyRobot));

        // when
        robotCommandService.controlRobot(ROBOT_ID, Commands.LEFT);

        // then
        verify(leftCommand, times(1))
                .executeCommand(toyRobot);
    }

    @Test
    void controlRobot_shouldInvokeMoveCommand_whenCommandIsRight() {
        // given
        ToyRobot toyRobot = ToyRobot.builder()
                .id(ROBOT_ID)
                .build();

        Mockito.when(repository.findById(ROBOT_ID))
                .thenReturn(Optional.of(toyRobot));

        // when
        robotCommandService.controlRobot(ROBOT_ID, Commands.RIGHT);

        // then
        verify(rightCommand, times(1))
                .executeCommand(toyRobot);
    }
}