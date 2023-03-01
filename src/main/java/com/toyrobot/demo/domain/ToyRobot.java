package com.toyrobot.demo.domain;

import com.toyrobot.demo.service.enums.FacingDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
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
