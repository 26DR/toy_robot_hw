package com.toyrobot.demo.service;

import com.toyrobot.demo.exception.ToyRobotException;
import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.repository.ToyRobotRepository;
import com.toyrobot.demo.service.commands.LeftCommand;
import com.toyrobot.demo.service.commands.MoveCommand;
import com.toyrobot.demo.service.commands.RightCommand;
import com.toyrobot.demo.service.enums.Commands;
import com.toyrobot.demo.service.enums.FacingDirection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*Command like pattern to encapsulate
information needed to trigger actions*/

@Service
@AllArgsConstructor
public class RobotCommandService {

    private final MoveCommand moveCommand;
    private final LeftCommand leftCommand;
    private final RightCommand rightCommand;
    private final ToyRobotRepository repository;

    private void moveRobot(ToyRobot toyRobot) {
        moveCommand.executeCommand(toyRobot);
        repository.save(toyRobot);
    }

    private void turnRobotLeft(ToyRobot toyRobot) {
        leftCommand.executeCommand(toyRobot);
        repository.save(toyRobot);
    }

    private void turnRobotRight(ToyRobot toyRobot) {
        rightCommand.executeCommand(toyRobot);
        repository.save(toyRobot);
    }

    public ToyRobot reportRobotPosition(Long id) {
        return getToyRobotById(id);
    }

    public ToyRobot placeRobot(int xPos, int yPos, FacingDirection facingDirection) {
        ToyRobot newRobot = RobotControl.placeANewRobot(xPos, yPos, facingDirection);
        repository.save(newRobot);
        return newRobot;
    }

    public ToyRobot controlRobot(Long id, Commands command) {
        ToyRobot toyRobot = getToyRobotById(id);

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

    //TODO Move this logic
    private ToyRobot getToyRobotById(Long id) {
        Optional<ToyRobot> toyRobot = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ToyRobotException("Robot not found")));

        return toyRobot.orElse(null);
    }
}
