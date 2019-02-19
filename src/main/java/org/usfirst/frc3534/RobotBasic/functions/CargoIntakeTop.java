package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.ShooterState;

public class CargoIntakeTop extends FunctionBase implements FunctionInterface{

    long originalTime = 0;
    boolean firstPartDone = false;

    public CargoIntakeTop(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!Robot.oi.getController2().getBButton() && firstPartDone){

            this.state = 30;
            firstPartDone = false;

        }

        if(!running && Robot.oi.getController2().getBButton()){

            this.reset();

        }

        switch(this.state) {
            case 0:
                if((Robot.oi.getController1().getBButton() && (!Robot.functionProcessor.cargoIntakeFloor.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))) {
                    this.started();
                    this.state = 10;
                }
                break;
    
            case 10:

                Robot.elevator.setElevatorState(ElevatorState.Floor);
                Robot.shooter.setShooterState(ShooterState.INTAKE);
                
                this.state = 20;

                break;

            case 20:

                firstPartDone = true;

                break;

            case 30:

                Robot.shooter.setShooterState(ShooterState.STOP);
                Robot.elevator.setElevatorState(ElevatorState.Floor);

                completed();

                break;
    
            }
        

    }

}