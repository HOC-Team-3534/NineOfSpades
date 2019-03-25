package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.OI.Axes;
import org.usfirst.frc3534.RobotBasic.RobotMap.FunctionStateDelay;
import org.usfirst.frc3534.RobotBasic.systems.HatchPanelApparatus.HatchIntakeState;
import org.usfirst.frc3534.RobotBasic.systems.HatchPanelApparatus.HatchPanelApparatusState;

public class HatchPlace extends FunctionBase implements FunctionInterface{

    long originalTime = 0;

    public HatchPlace(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!running && Axes.HatchPlace.getAxis() >= 0.9){

            this.reset();

        }

        switch(this.state) {

        case 0:

            if((Axes.HatchPlace.getAxis() >= 0.5 && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.cargoIntakeFloor.isRunning())) && !Robot.functionProcessor.cargoShoot.isRunning()) {
                
                this.started();
                this.state = 10;

            }
            
            break;

        case 10:

            originalTime = System.currentTimeMillis();    
            Robot.hatchPanelApparatus.setHatchIntakeState(HatchIntakeState.RELEASE);
            this.state = 20;

            break;

        case 20:

            if(System.currentTimeMillis() - originalTime > RobotMap.FunctionStateDelay.hatchPlace_hatchIntakeRelease_to_hatchPanelApparatusExtended.time){

                this.state = 30;

            }

            break;

        case 30:

            originalTime = System.currentTimeMillis();
            Robot.hatchPanelApparatus.setHatchPanelApparatusState(HatchPanelApparatusState.EXTENDED);
            this.state = 40;
            
            break;

        case 40:

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.hatchPlace_hatchPanelApparatusExtended_to_hatchPanelApparatusCollapsed.time){

                this.state = 50;

            }

            break;

        case 50:
            
            originalTime = System.currentTimeMillis();
            Robot.hatchPanelApparatus.setHatchPanelApparatusState(HatchPanelApparatusState.COLLAPSED);
            this.state = 60;

            break;

        case 60:

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.hatchPlace_hatchPanelApparatusCollapsed_to_hatchPlaceCompleted.time){

                completed();

            }

            break;

        }
    }
}