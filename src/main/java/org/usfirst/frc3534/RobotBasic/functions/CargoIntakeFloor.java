package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmExtendState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.RollerState;

public class CargoIntakeFloor extends FunctionBase implements FunctionInterface{

    long originalTime = 0;
    boolean firstPartDone = false;

    public CargoIntakeFloor(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!Robot.oi.getController1().getAButton() && firstPartDone){

            this.state = 40;
            firstPartDone = false;

        }

        if(!running && Robot.oi.getController1().getAButton()){

            this.reset();

        }

        switch(this.state) {
            case 0:
                if((Robot.oi.getController1().getAButton() && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))) {
                    this.started();
                    this.state = 10;
                    originalTime = System.currentTimeMillis();
                }
                break;
    
            case 10:

                Robot.elevator.setElevatorState(ElevatorState.Stage1A);
                
                if(System.currentTimeMillis() - originalTime > 3.0 * 1000){
                    this.state = 20;
                }
                break;

            case 20:

                Robot.intake.setArmExtendState(ArmExtendState.EXTENDED);
                Robot.intake.setRollerState(RollerState.INTAKE);

                this.state = 30;

                break;

            case 30:

                firstPartDone = true;

                break;

            case 40:

                Robot.intake.setArmExtendState(ArmExtendState.COLLAPSED);

                if(!Robot.intake.isArmAft()){

                    this.state = 50;

                }

                break;

            case 50:

                Robot.intake.setRollerState(RollerState.STOP);
                Robot.elevator.setElevatorState(ElevatorState.Floor);

                completed();

                break;
    
        }

    }

}