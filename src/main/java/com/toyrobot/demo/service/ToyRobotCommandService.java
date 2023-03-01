package com.toyrobot.demo.service;

import com.toyrobot.demo.domain.ToyRobot;
import com.toyrobot.demo.service.commands.LeftCommand;
import com.toyrobot.demo.service.commands.MoveCommand;
import com.toyrobot.demo.service.commands.RightCommand;
import com.toyrobot.demo.service.enums.Commands;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/*Command like pattern to encapsulate
information needed to trigger actions*/

@Service
@AllArgsConstructor
public class ToyRobotCommandService {

    private final MoveCommand moveCommand;
    private final LeftCommand leftCommand;
    private final RightCommand rightCommand;
    private final ToyRobotService toyRobotService;

    private void moveRobot(ToyRobot toyRobot) {
        moveCommand.executeCommand(toyRobot);
        toyRobotService.saveToyRobot(toyRobot);
    }

    private void turnRobotLeft(ToyRobot toyRobot) {
        leftCommand.executeCommand(toyRobot);
        toyRobotService.saveToyRobot(toyRobot);
    }

    private void turnRobotRight(ToyRobot toyRobot) {
        rightCommand.executeCommand(toyRobot);
        toyRobotService.saveToyRobot(toyRobot);
    }

    public ToyRobot controlRobot(Long id, Commands command) {
        ToyRobot toyRobot = toyRobotService.findById(id);

        switch (command) {
            case MOVE:
                moveRobot(toyRobot);
                break;
            case LEFT:
                turnRobotLeft(toyRobot);
                break;
            case RIGHT:
                turnRobotRight(toyRobot);
                break;
        }

        return toyRobot;
    }
}
