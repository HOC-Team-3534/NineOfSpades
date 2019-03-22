package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.systems.Climber.SuperThrustersState;

public class HabLevel3ClimbPart2 extends FunctionBase implements FunctionInterface{

    long originalTime = 0;
    boolean firstPartDone = false;

    public HabLevel3ClimbPart2(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(((!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.cargoIntakeFloor.isRunning()) && !Robot.functionProcessor.habLevel3ClimbPart2.isRunning())) {

            if(Buttons.HabLevel3ClimbPart2Thrust.getButton() && Buttons.HabLevel3ClimbPart2Lockout.getButton()){

                started();
                Robot.climber.setSuperThrustersState(SuperThrustersState.Thrust);

            }else if(Buttons.HabLevel3ClimbPart2Back.getButton() && Buttons.HabLevel3ClimbPart2Lockout.getButton()){

                started();
                Robot.climber.setSuperThrustersState(SuperThrustersState.Retract);

            }else{

                reset();
                completed();
                Robot.climber.setSuperThrustersState(SuperThrustersState.OFF);

            }

        }

    }
}