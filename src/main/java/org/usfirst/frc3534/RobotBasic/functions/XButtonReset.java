package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.RobotMap.FunctionStateDelay;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmExtendState;

public class XButtonReset extends FunctionBase implements FunctionInterface {

    long originalTime = 0;

    public XButtonReset() {

        this.reset();
        this.completed();

    }

    @Override
    public void process() {

        if((!running && Buttons.XButtonReset.getButton() && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))){

            this.reset();

        }
        
        switch(this.state) {

        case 0:

            if(((Buttons.XButtonReset.getButton() && Robot.elevator.getElevatorState() != ElevatorState.Floor) && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.cargoIntakeFloor.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))) {

                this.started();
                this.state = 10;

            }

            break;

        case 10:

            originalTime = System.currentTimeMillis();
            this.state = 20;
            
            break;

        case 20:

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.xButtonReset_armLiftMid_to_armExtendCollapsed.time){

                this.state = 30;

            }

            break;

        case 30:

            Robot.intake.setArmExtendState(ArmExtendState.COLLAPSED);
            this.state = 40;

            break;

        case 40:

            if(Robot.intake.isArmAft()){

                this.state = 50;

            }

            break;

        case 50:

            Robot.elevator.setElevatorState(ElevatorState.Floor);
            this.state = 60;

            break;

        case 60:

            completed();
            
            break;

        }
    }
}