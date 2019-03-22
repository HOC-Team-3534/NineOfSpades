package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;

public class Elevate extends FunctionBase implements FunctionInterface{

    public Elevate(){

        reset();

    }

    @Override
    public void process(){

        if(!Robot.functionProcessor.cargoIntakeFloor.isRunning() && ((!Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && !Robot.functionProcessor.habLevel3ClimbPart2.isRunning())){

            if(Buttons.Elevate_Stage1A.getButton()){

                if(Robot.elevator.getElevatorState() != ElevatorState.Stage1A) Robot.elevator.setElevatorState(ElevatorState.Stage1A);

            }else if(Buttons.Elevate_Stage1B.getButton()){

                if(Robot.elevator.getElevatorState() != ElevatorState.Stage1B) Robot.elevator.setElevatorState(ElevatorState.Stage1B);

            }else if(Buttons.Elevate_Stage2.getButton()){

                if(Robot.elevator.getElevatorState() != ElevatorState.Stage2) Robot.elevator.setElevatorState(ElevatorState.Stage2);

            }else{

                if((Robot.elevator.getElevatorState() != ElevatorState.NULL && Robot.elevator.getElevatorState() != ElevatorState.OFF) && Robot.elevator.getElevatorState() != ElevatorState.Floor){

                    Robot.elevator.setElevatorState(ElevatorState.Floor);

                }
            }
        }
    }
}