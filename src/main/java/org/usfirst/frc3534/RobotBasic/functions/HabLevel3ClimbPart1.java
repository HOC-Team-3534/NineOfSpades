package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap.FunctionStateDelay;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmExtendState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmLiftState;

public class HabLevel3ClimbPart1 extends FunctionBase implements FunctionInterface{

    long originalTime = 0;

    public HabLevel3ClimbPart1(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!running && Robot.oi.getController2().getXButton()){

            this.reset();

        }

        switch(this.state) {

        case 0:

            if((Robot.oi.getController2().getXButton() && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.cargoIntakeFloor.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))) {
               
                this.started();
                this.state = 10;

            }

            break;

        case 10:

            originalTime = System.currentTimeMillis();
            Robot.elevator.setElevatorState(ElevatorState.Stage1A);
            this.state = 20;

            break;

        case 20:

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.habLevel3ClimbPart1_elevatorStage1A_to_armExtendExtended.time){

                this.state = 30;
                
            }
        
            break;

        case 30:
            
            originalTime = System.currentTimeMillis();
            Robot.intake.setArmExtendState(ArmExtendState.EXTENDED);
            this.state = 40;

            break;

        case 40:

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.habLevel3ClimbPart1_armExtendExtended_to_armLiftUp.time){

                this.state = 50;

            }

            break;

        case 50:

            Robot.intake.setArmLiftState(ArmLiftState.UP);
            this.state = 60;

            break;

        case 60:

            completed();

            break;

        }
    }
}