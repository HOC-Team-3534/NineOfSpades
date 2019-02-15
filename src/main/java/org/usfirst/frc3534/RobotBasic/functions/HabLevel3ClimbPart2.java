package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;

public class HabLevel3ClimbPart2 extends FunctionBase implements FunctionInterface{

    public HabLevel3ClimbPart2(){

        reset();

    }

    @Override
    public void process(){

        switch(this.state) {
            case 0:
                if(Robot.oi.getController1().getXButton()) {
                    this.started();
                    this.state = 10;
                }
                break;
    
            case 10:
                
                break;
    
            }
        
    }

}