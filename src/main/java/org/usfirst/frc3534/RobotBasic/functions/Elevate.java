package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmLiftState;

public class Elevate extends FunctionBase implements FunctionInterface{

    public Elevate(){

        reset();

    }

    @Override
    public void process(){

        if(((!Robot.functionProcessor.cargoIntakeFloor.isRunning() && !Robot.functionProcessor.cargoIntakeTop.isRunning()) && Robot.intake.getArmLiftState() != ArmLiftState.UP ) && ((!Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))){

            if(Buttons.Elevate_Stage1A.getButton()){

                Robot.elevator.setElevatorState(ElevatorState.Stage1A);

            }else if(Buttons.Elevate_Stage1B.getButton()){

                Robot.elevator.setElevatorState(ElevatorState.Stage1B);

            }else if(Buttons.Elevate_Stage2.getButton()){

                Robot.elevator.setElevatorState(ElevatorState.Stage2);

            }else{

                if(Robot.elevator.getElevatorState() != ElevatorState.NULL && Robot.elevator.getElevatorState() != ElevatorState.OFF){

                    Robot.elevator.setElevatorState(ElevatorState.Floor);

                }
            }
        }
    }
}