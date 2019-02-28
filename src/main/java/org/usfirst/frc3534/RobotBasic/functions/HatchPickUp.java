package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.systems.HatchPanelApparatus.HatchIntakeState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmLiftState;

public class HatchPickUp extends FunctionBase implements FunctionInterface{

    public HatchPickUp(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!running && Buttons.HatchPickUp.getButton()){

            this.reset();

        }

        switch(this.state){

        case 0:

            if(((Buttons.HatchPickUp.getButton() && (Robot.intake.getArmLiftState() != ArmLiftState.UP && !(Robot.intake.getArmLiftState() == ArmLiftState.OFF && Robot.intake.getPrevArmLiftState() == ArmLiftState.UP))) && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.cargoIntakeFloor.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))) {
                    
                this.started();
                this.state = 10;

            }

            break;

        case 10:

            Robot.hatchPanelApparatus.setHatchIntakeState(HatchIntakeState.HOLD);
            this.state = 20;

            break;

        case 20:

            completed();

            break;

        }

    }

}