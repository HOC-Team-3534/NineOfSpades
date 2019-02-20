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

            if((Robot.oi.getController2().getTriggerAxis(Hand.kRight) >= 0.5 && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.cargoIntakeFloor.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))) {
                
                this.started();
                this.state = 10;

            }
            
            break;

        case 10:

            originalTime = System.currentTimeMillis();
            Robot.hatchPanelApparatus.setHatchPanelApparatusState(HatchPanelApparatusState.EXTENDED);
            this.state = 20;
            
            break;

        case 20:

            if(System.currentTimeMillis() - originalTime > .75 * 1000){

                this.state = 30;

            }

            break;

        case 30:
            
            Robot.hatchPanelApparatus.setHatchPanelApparatusState(HatchPanelApparatusState.COLLAPSED);
            this.state = 40;

            break;

        case 40:

            completed();

            break;

        }
    }
}