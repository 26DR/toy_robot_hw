package com.toyrobot.demo.repository;

import com.toyrobot.demo.domain.ToyRobot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRobotRepository extends CrudRepository<ToyRobot, Long> {

}
