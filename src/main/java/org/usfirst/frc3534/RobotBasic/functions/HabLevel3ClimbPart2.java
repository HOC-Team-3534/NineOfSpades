package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;

public class HabLevel3ClimbPart2 extends FunctionBase implements FunctionInterface{

    long originalTime = 0;
    boolean firstPartDone = false;

    public HabLevel3ClimbPart2(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!Robot.oi.getController1().getXButton() && firstPartDone){

            this.state = 40;
            firstPartDone = false;

        }

        if(!running && Robot.oi.getController1().getXButton()){

            this.reset();

        }

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