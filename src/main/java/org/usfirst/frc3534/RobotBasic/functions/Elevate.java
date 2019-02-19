package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;

public class Elevate extends FunctionBase implements FunctionInterface{

    public Elevate(){

        reset();

    }

    @Override
    public void process(){

        if((!Robot.functionProcessor.cargoIntakeFloor.running && !Robot.functionProcessor.cargoIntakeTop.running) && ((!Robot.functionProcessor.habLevel3ClimbPart1.running) && (!Robot.functionProcessor.habLevel3ClimbPart2.running && !Robot.functionProcessor.xButtonReset.running))){

            if(Robot.oi.getController2().getAButton()){

                Robot.elevator.setElevatorState(ElevatorState.Stage1A);

            }else if(Robot.oi.getController2().getBButton()){

                Robot.elevator.setElevatorState(ElevatorState.Stage1B);

            }else if(Robot.oi.getController2().getYButton()){

                Robot.elevator.setElevatorState(ElevatorState.Stage2);

            }else{

                if(Robot.elevator.getElevatorState() != ElevatorState.NULL){

                    Robot.elevator.setElevatorState(ElevatorState.Floor);

                }

            }

        }

    }

}