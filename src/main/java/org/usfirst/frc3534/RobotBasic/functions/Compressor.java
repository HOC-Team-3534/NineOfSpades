package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmLiftState;

public class Compressor extends FunctionBase implements FunctionInterface{

    public Compressor(){

        reset();

    }

    @Override
    public void process(){

        if(Robot.oi.getController1().getBackButton()){

            RobotMap.compressor.stop();

        }else if(Robot.oi.getController1().getStartButton()){

            RobotMap.compressor.start();

        }

    }
}