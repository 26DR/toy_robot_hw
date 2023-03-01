package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.domain.ToyRobot;
import com.toyrobot.demo.service.ToyRobotActionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeftCommand extends Command {

    ToyRobotActionsService toyRobotActionsService;

    @Override
    public void executeCommand(ToyRobot toyRobot) {
        toyRobotActionsService.turnRobotLeft(toyRobot);
    }
}
