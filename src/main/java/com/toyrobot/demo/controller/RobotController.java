package com.toyrobot.demo.controller;

import com.toyrobot.demo.domain.ToyRobot;
import com.toyrobot.demo.service.ToyRobotActionsService;
import com.toyrobot.demo.service.ToyRobotCommandService;
import com.toyrobot.demo.service.enums.Commands;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/robot")
public class RobotController {

    private final ToyRobotCommandService toyRobotCommandService;
    private final ToyRobotActionsService toyRobotActionsService;

    @Operation(summary = "Create and place robot on the tabletop",
            description = "Create and place robot on a square tabletop (5x5) grid.")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Long> initiateRobot(@Valid @RequestBody ToyRobot toyRobot) {
        return ResponseEntity.ok(toyRobotActionsService.placeRobot(toyRobot.getXPos(), toyRobot.getYPos(),
                toyRobot.getFacingDirection()));
    }

    @Operation(summary = "Control robot by giving it commands",
            description = "Give robot commands such as LEFT, RIGHT or MOVE to control the robot")
    @PostMapping(path = "/{id}/{command}")
    @ResponseBody
    public ResponseEntity<ToyRobot> controlRobot(@PathVariable Long id, @PathVariable("command") Commands command) {
        return ResponseEntity.ok(toyRobotCommandService.controlRobot(id, command));
    }

    @Operation(summary = "Retrieve robot coordinates",
            description = "Retrieve robot current coordinates by a robot ID")
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ToyRobot> getRobotReport(@PathVariable Long id) {
        return ResponseEntity.ok(toyRobotActionsService.reportRobotPosition(id));
    }
}
