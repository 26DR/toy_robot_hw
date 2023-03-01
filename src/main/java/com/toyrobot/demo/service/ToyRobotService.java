package com.toyrobot.demo.service;

import com.toyrobot.demo.exception.ToyRobotNotFoundException;
import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.repository.ToyRobotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ToyRobotService {

    private final ToyRobotRepository toyRobotRepository;

    public ToyRobot findById(Long id) {
        Optional<ToyRobot> toyRobot = Optional.ofNullable(toyRobotRepository.findById(id)
                .orElseThrow(() -> new ToyRobotNotFoundException("Robot not found")));
        return toyRobot.orElse(null);
    }

    public void saveToyRobot(ToyRobot toyRobot) {
        toyRobotRepository.save(toyRobot);
    }
}
