package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmExtendState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmLiftState;

public class XButtonReset extends FunctionBase implements FunctionInterface {

    long originalTime = 0;

    public XButtonReset() {

        this.reset();
        this.completed();

    }

    @Override
    public void process() {

        if((!running && Robot.oi.getController2().getBackButton() && (!Robot.functionProcessor.cargoIntakeTop.running && !Robot.functionProcessor.hatchPlace.running)) && ((!Robot.functionProcessor.cargoShoot.running && !Robot.functionProcessor.habLevel3ClimbPart1.running) && (!Robot.functionProcessor.habLevel3ClimbPart2.running && !Robot.functionProcessor.xButtonReset.running))){

            this.reset();

        }
        
        switch(this.state) {
        case 0:

            if(Robot.oi.getController2().getBackButton()) {
                this.started();
                this.state = 10;
                originalTime = System.currentTimeMillis();
            }
            break;

        case 10:

            Robot.intake.setArmLiftState(ArmLiftState.MID);

            if(System.currentTimeMillis() - originalTime > 3.0 * 1000){

                this.state = 20;

            }
            
            break;

        case 20:

            Robot.intake.setArmExtendState(ArmExtendState.COLLAPSED);
           
            if(Robot.intake.isArmAft()){

                this.state = 30;

            }

            break;

        case 30:

            Robot.elevator.setElevatorState(ElevatorState.Floor);

            this.state = 40;

            break;

        case 40:

            completed();
            
            break;

        }

    }

}