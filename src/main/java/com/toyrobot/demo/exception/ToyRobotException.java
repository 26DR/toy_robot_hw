package com.toyrobot.demo.exception;

public class ToyRobotException extends RuntimeException {
    public ToyRobotException(){
        super();
    }

    public ToyRobotException(String message){
        super(message);
    }

    public ToyRobotException(String message, Throwable cause){
        super(message, cause);
    }
}
