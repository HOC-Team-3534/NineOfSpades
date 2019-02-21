package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
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

        if(!Buttons.CargoIntakeTop.getButton() && firstPartDone){

            this.state = 30;
            firstPartDone = false;

        }

        if(!running && Buttons.CargoIntakeTop.getButton()){

            this.reset();

        }

        switch(this.state) {

        case 0:

            if((Buttons.CargoIntakeTop.getButton() && (!Robot.functionProcessor.cargoIntakeFloor.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && ((!Robot.functionProcessor.cargoShoot.isRunning() && !Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))) {
               
                this.started();
                this.state = 10;

            }

            break;

        case 10:

            Robot.shooter.setShooterState(ShooterState.INTAKE);
            this.state = 20;

            break;

        case 20:

            firstPartDone = true;

            break;

        case 30:

            Robot.shooter.setShooterState(ShooterState.STOP);
            this.state = 40;   

            break;

        case 40:

            completed();

            break;
    
        }
    }
}