package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.ShooterState;

public class CargoIntakeFloor extends FunctionBase implements FunctionInterface{

    public CargoIntakeFloor(){

        reset();

    }

    @Override
    public void process(){

        if(!Robot.oi.getController2().getAButton()){

            this.state = 30;

        }

        if(!running && Robot.oi.getController2().getAButton()){

            this.reset();

        }

        switch(this.state) {
            case 0:
                if(Robot.oi.getController1().getAButton()) {
                    this.started();
                    this.state = 10;
                }
                break;
    
            case 10:

                
                
                break;
    
            }
        
    }

}