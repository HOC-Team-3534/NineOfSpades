package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;

public class Compressor extends FunctionBase implements FunctionInterface{

    boolean on = true;

    public Compressor(){

        reset();

    }

    @Override
    public void process(){

        if(Robot.oi.getController1().getBackButton()){

            on = false;

        }else if(Robot.oi.getController1().getStartButton()){

            on = true;

        }

        if(on){

            RobotMap.compressor.start();

        }else{

            RobotMap.compressor.stop();

        }

    }
}