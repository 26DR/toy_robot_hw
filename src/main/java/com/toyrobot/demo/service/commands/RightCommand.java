package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.model.ToyRobot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RightCommand extends Command<Boolean>{

    RobotActions robotActions;

    @Override
    public Boolean executeCommand(ToyRobot toyRobot) {
        return robotActions.turnRobotRight(toyRobot);
    }
}
