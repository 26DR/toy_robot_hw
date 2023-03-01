package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.model.ToyRobot;

public abstract class Command {
    public abstract void executeCommand(ToyRobot toyRobot);
}
