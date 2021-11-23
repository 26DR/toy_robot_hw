package com.toyrobot.demo.model;

import com.toyrobot.demo.service.enums.FacingDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ToyRobot {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Horizontal
    private int xPos;
    //Vertical
    private int yPos;
    //Facing direction represented as NORTH 1, EAST 2, SOUTH 3, WEST 4
    private FacingDirection facingDirection;
}
