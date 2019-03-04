package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.RobotMap.FunctionStateDelay;
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

        if(!Buttons.HabLevel3ClimbPart2.getButton() && firstPartDone){

            this.state = 50;
            firstPartDone = false;

        }

        if(!running && Buttons.HabLevel3ClimbPart2.getButton()){

            this.reset();

        }

        switch(this.state) {

        case 0:

            if(((Buttons.HabLevel3ClimbPart2.getButton() && (Robot.intake.getArmLiftState() == ArmLiftState.UP || (Robot.intake.getArmLiftState() == ArmLiftState.OFF && Robot.intake.getPrevArmLiftState() == ArmLiftState.UP)) && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.cargoIntakeFloor.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))) {
                
                this.started();
                this.state = 10;
                
            }
            
            break;

        case 10:
            
            originalTime = System.currentTimeMillis();
            Robot.intake.setArmLiftState(ArmLiftState.COLLAPSED);
            this.state = 20;

            break;

        case 20:

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.habLevel3ClimbPart2_armLiftCollapsed_to_climberClimb.time){

                this.state = 30;

            }

            break;

        case 30:

            Robot.climber.setClimberState(ClimberState.Climb);
            this.state = 40;

            break;

        case 40:
            
            firstPartDone = true;
            
            break;

        case 50:

            originalTime = System.currentTimeMillis();
            Robot.intake.setArmLiftState(ArmLiftState.MID);
            Robot.climber.setClimberState(ClimberState.Retract);
            this.state = 60;

            break;

        case 60:

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.habLevel3ClimbPart2_armLiftMid_climberRetract_to_armExtendCollapsed.time){

                this.state = 70;

            }

            break;

        case 70:

            Robot.intake.setArmExtendState(ArmExtendState.COLLAPSED);
            this.state = 80;

            break;

        case 80:

            if(Robot.intake.isArmAft()) {

                this.state = 90;

            }

            break;

        case 90:

            Robot.elevator.setElevatorState(ElevatorState.Floor);
            this.state = 100;

            break;

        case 100:
            
            completed();

            break;
    
        }
    }
}