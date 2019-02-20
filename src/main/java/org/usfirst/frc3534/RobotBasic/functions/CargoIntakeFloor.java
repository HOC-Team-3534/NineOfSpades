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

            this.state = 50;
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
                }
                break;
    
            case 10:

                originalTime = System.currentTimeMillis();
                Robot.elevator.setElevatorState(ElevatorState.Stage1A);
                this.state = 20;
                
                break;

            case 20:

                if(System.currentTimeMillis() - originalTime > 3.0 * 1000){
                    
                    this.state = 30;

                }

                break;

            case 30:

                Robot.intake.setArmExtendState(ArmExtendState.EXTENDED);
                Robot.intake.setRollerState(RollerState.INTAKE);

                this.state = 40;

                break;

            case 40:

                firstPartDone = true;

                break;

            case 50:

                Robot.intake.setArmExtendState(ArmExtendState.COLLAPSED);
                this.state = 60;

                break;

            case 60:

                if(!Robot.intake.isArmAft()){

                    this.state = 70;

                }

                break;

            case 70:

                Robot.intake.setRollerState(RollerState.STOP);
                Robot.elevator.setElevatorState(ElevatorState.Floor);

                completed();

                break;
    
        }

    }

}