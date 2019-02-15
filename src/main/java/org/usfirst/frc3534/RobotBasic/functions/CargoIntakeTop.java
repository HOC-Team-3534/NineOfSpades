package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmExtendState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.RollerState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.ShooterState;

public class CargoIntakeTop extends FunctionBase implements FunctionInterface{

    long originalTime = 0;

    public CargoIntakeTop(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!Robot.oi.getController2().getBButton()){

            this.state = 40;

        }

        if(!running && Robot.oi.getController2().getBButton()){

            this.reset();

        }

        switch(this.state) {
            case 0:
                if(Robot.oi.getController1().getBButton()) {
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
                Robot.intake.setRollerState(RollerState.INTAKE);

                this.state = 30;

                break;

            case 30:

                break;

            case 40:

                

                break;
    
            }

    }

}