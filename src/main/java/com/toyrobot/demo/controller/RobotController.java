package com.toyrobot.demo.controller;

import com.toyrobot.demo.model.ToyRobot;
import com.toyrobot.demo.service.RobotCommandService;
import com.toyrobot.demo.service.enums.Commands;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@AllArgsConstructor
public class RobotController {

    private final RobotCommandService robotCommandService;

    @PostMapping(path = "/robot/place")
    @ResponseBody
    public ResponseEntity<ToyRobot> initiateRobot(@RequestBody ToyRobot toyRobot) {
        try {
            return ResponseEntity.ok(robotCommandService.placeRobot(toyRobot.getXPos(), toyRobot.getYPos(),
                    toyRobot.getFacingDirection()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    e);
        }
    }

    @PostMapping(path = "/robot/{id}/{command}")
    @ResponseBody
    public ResponseEntity<ToyRobot> controlRobot(@PathVariable Long id, @PathVariable("command") Commands command) {
        try {
            return ResponseEntity.ok(robotCommandService.controlRobot(id, command));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    e);
        }
    }

    @GetMapping(path = "/robot/{id}")
    @ResponseBody
    public ResponseEntity<ToyRobot> getRobotReport(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(robotCommandService.reportRobotPosition(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    e);
        }
    }
}
