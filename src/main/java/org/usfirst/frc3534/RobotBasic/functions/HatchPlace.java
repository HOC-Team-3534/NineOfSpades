package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

public class HatchPlace extends FunctionBase implements FunctionInterface{

    public HatchPlace(){

        reset();

    }

    @Override
    public void process(){

        switch(this.state) {
            case 0:
                if(Robot.oi.getController2().getTriggerAxis(Hand.kRight) >= 0.5) {
                    this.started();
                    this.state = 10;
                }
                break;
    
            case 10:
                
                break;
    
            }

    }

}