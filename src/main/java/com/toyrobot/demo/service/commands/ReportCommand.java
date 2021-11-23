package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.model.ToyRobot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportCommand extends Command<String> {

    RobotActions robotActions;

    @Override
    public String executeCommand(ToyRobot toyRobot) {
        return robotActions.robotReport(toyRobot);
    }
}
