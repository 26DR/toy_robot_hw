package com.toyrobot.demo.service;

import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.service.commands.LeftCommand;
import com.toyrobot.demo.service.commands.MoveCommand;
import com.toyrobot.demo.service.commands.RightCommand;
import com.toyrobot.demo.service.enums.Commands;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ToyRobotCommandServiceTest {

    public static final long ROBOT_ID = 1L;

    @InjectMocks
    private ToyRobotCommandService toyRobotCommandService;

    @Mock
    private MoveCommand moveCommand;

    @Mock
    private LeftCommand leftCommand;

    @Mock
    private RightCommand rightCommand;

    @Mock
    private ToyRobotService toyRobotService;

    @ParameterizedTest(name = "{index} => command=''{0}''")
    @EnumSource(Commands.class)
    void controlRobot_shouldInvokeSave_whenAnyCommandUsed(Commands command) {
        // given
        ToyRobot toyRobot = ToyRobot.builder()
                .id(ROBOT_ID)
                .build();

        Mockito.when(toyRobotService.findById(ROBOT_ID))
                .thenReturn(toyRobot);

        // when
        toyRobotCommandService.controlRobot(ROBOT_ID, command);

        // then
        verify(toyRobotService, times(1))
                .saveToyRobot(toyRobot);
    }

    @Test
    void controlRobot_shouldInvokeMoveCommand_whenCommandIsMove() {
        // given
        ToyRobot toyRobot = ToyRobot.builder()
                .id(ROBOT_ID)
                .build();

        Mockito.when(toyRobotService.findById(ROBOT_ID))
                .thenReturn(toyRobot);

        // when
        toyRobotCommandService.controlRobot(ROBOT_ID, Commands.MOVE);

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

        Mockito.when(toyRobotService.findById(ROBOT_ID))
                .thenReturn(toyRobot);

        // when
        toyRobotCommandService.controlRobot(ROBOT_ID, Commands.LEFT);

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

        Mockito.when(toyRobotService.findById(ROBOT_ID))
                .thenReturn(toyRobot);

        // when
        toyRobotCommandService.controlRobot(ROBOT_ID, Commands.RIGHT);

        // then
        verify(rightCommand, times(1))
                .executeCommand(toyRobot);
    }
}