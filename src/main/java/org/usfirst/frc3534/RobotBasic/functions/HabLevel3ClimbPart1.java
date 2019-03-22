package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.RobotMap.FunctionStateDelay;
import org.usfirst.frc3534.RobotBasic.systems.Climber.ClimberState;
import org.usfirst.frc3534.RobotBasic.systems.Climber.SuperThrustersState;

public class HabLevel3ClimbPart1 extends FunctionBase implements FunctionInterface{

    long originalTime = 0;
    int climbState = 0;

    public HabLevel3ClimbPart1(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(((!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.cargoIntakeFloor.isRunning()) && !Robot.functionProcessor.habLevel3ClimbPart2.isRunning())) {

            if(Buttons.HabLevel3ClimbPart1Up.getButton() && Buttons.HabLevel3ClimbPart1Lockout.getButton()){

                started();

                switch(climbState){

                case 0:

                    Robot.climber.setSuperThrustersState(SuperThrustersState.Retract);
                    climbState = 10;
                    originalTime = System.currentTimeMillis();

                    break;

                case 10:

                    if(System.currentTimeMillis() - originalTime > FunctionStateDelay.habLevel3ClimbPart1_superThrustersRetract_to_climberClimb.time){

                        climbState = 20;

                    }

                    break;

                case 20:

                    Robot.climber.setClimberState(ClimberState.Climb);

                    break;

                }

            }else if(Buttons.HabLevel3ClimbPart1Down.getButton() && Buttons.HabLevel3ClimbPart1Lockout.getButton()){

                started();
                climbState = 0;
                Robot.climber.setClimberState(ClimberState.Retract);

            }else{

                reset();
                completed();
                climbState = 0;
                Robot.climber.setClimberState(ClimberState.OFF);

            }

        }

    }
}