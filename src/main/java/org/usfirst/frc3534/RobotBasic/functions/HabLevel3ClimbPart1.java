package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
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
            if(Robot.oi.getController2().getXButton()) {
                this.started();
                this.state = 10;
                originalTime = System.currentTimeMillis();
            }
            break;

        case 10:

            Robot.elevator.setElevatorState(ElevatorState.Stage1A);
            if(System.currentTimeMillis() - originalTime > .75 * 1000){

                this.state = 20;
                originalTime = System.currentTimeMillis();
            }
            break;

        case 20:
            
            Robot.intake.setArmExtendState(ArmExtendState.EXTENDED);
            if(System.currentTimeMillis() - originalTime > .75 * 1000){

                this.state = 30;

            }
            break;

        case 30:

            Robot.intake.setArmLiftState(ArmLiftState.UP);
            this.state = 40;

            break;

        case 40:

            completed();

            break;

        }

    }

}