package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.HatchPanelApparatus.HatchPanelApparatusState;

import edu.wpi.first.wpilibj.GenericHID.Hand;

public class HatchPlace extends FunctionBase implements FunctionInterface{

    long originalTime = 0;

    public HatchPlace(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!running && Robot.oi.getController2().getTriggerAxis(Hand.kRight) >= 0.5){

            this.reset();

        }

        switch(this.state) {
            case 0:
                if(Robot.oi.getController2().getTriggerAxis(Hand.kRight) >= 0.5) {
                    this.started();
                    this.state = 10;
                    originalTime = System.currentTimeMillis();
                }
                break;
    
            case 10:

                Robot.hatchPanelApparatus.setHatchPanelApparatusState(HatchPanelApparatusState.EXTENDED);
                if(System.currentTimeMillis() - originalTime > .75 * 1000){

                    this.state = 20;

                }
                
                break;

            case 20:
                
                Robot.hatchPanelApparatus.setHatchPanelApparatusState(HatchPanelApparatusState.COLLAPSED);
                this.state = 30;

                break;

            case 30:

                completed();

                break;
    
            }

    }

}