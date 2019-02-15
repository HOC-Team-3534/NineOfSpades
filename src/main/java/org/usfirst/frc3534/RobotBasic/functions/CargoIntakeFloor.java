package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;

public class CargoIntakeFloor extends FunctionBase implements FunctionInterface{

    public CargoIntakeFloor(){

        reset();

    }

    @Override
    public void process(){

        switch(this.state) {
            case 0:
                if(Robot.oi.getController1().getAButton()) {
                    this.started();
                    this.state = 10;
                }
                break;
    
            case 10:
                
                break;
    
            }
        
    }

}