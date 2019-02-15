package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;

public class XButtonReset extends FunctionBase implements FunctionInterface {

    public XButtonReset() {

        this.reset();

    }

    @Override
    public void process() {
        
        switch(this.state) {
        case 0:

            if(Robot.oi.getController2().getBackButton()) {
                this.started();
                this.state = 10;
            }
            break;

        case 10:
            
            break;

        }

    }

}