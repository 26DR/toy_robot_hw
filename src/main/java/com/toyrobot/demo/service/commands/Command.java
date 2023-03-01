package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.domain.ToyRobot;

public abstract class Command {
    public abstract void executeCommand(ToyRobot toyRobot);
}
