package com.toyrobot.demo.service.commands;

import com.toyrobot.demo.model.ToyRobot;

public abstract class Command<T> {
    public abstract T executeCommand(ToyRobot toyRobot);
}
