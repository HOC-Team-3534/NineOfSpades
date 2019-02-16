package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.Climber.ClimberState;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmExtendState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmLiftState;

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
            originalTime = System.currentTimeMillis();

        }

        if(!running && Robot.oi.getController1().getXButton()){

            this.reset();

        }

        switch(this.state) {
            case 0:
                if(((Robot.oi.getController1().getXButton() && Robot.oi.getController2().getXButton()) && (!Robot.functionProcessor.cargoIntakeTop.running && !Robot.functionProcessor.hatchPlace.running)) && ((!Robot.functionProcessor.cargoShoot.running && !Robot.functionProcessor.habLevel3ClimbPart1.running) && (!Robot.functionProcessor.cargoIntakeFloor.running && !Robot.functionProcessor.xButtonReset.running))) {
                    this.started();
                    this.state = 10;
                    originalTime = System.currentTimeMillis();
                }
                break;
    
            case 10:
                
                Robot.intake.setArmLiftState(ArmLiftState.COLLAPSED);
                if(System.currentTimeMillis() - originalTime > .75 * 1000){

                    this.state = 20;
    
                }
                break;

            case 20:

                Robot.climber.setClimberState(ClimberState.Climb);
                this.state = 30;
                break;

            case 30:
                
                firstPartDone = true;
                
                break;

            case 40:

                Robot.intake.setArmLiftState(ArmLiftState.MID);
                Robot.climber.setClimberState(ClimberState.Retract);
                if(System.currentTimeMillis() - originalTime > .75 * 1000){

                    this.state = 50;
    
                }
                break;

            case 50:

                Robot.intake.setArmExtendState(ArmExtendState.COLLAPSED);
                if(Robot.intake.isArmAft()) {

                    this.state = 60;

                }

                break;

            case 60:

                Robot.elevator.setElevatorState(ElevatorState.Floor);
                this.state = 70;
                break;

            case 70:
                
                completed();

                break;
    
            }
        
    }

}