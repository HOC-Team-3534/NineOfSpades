package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.RobotMap.FunctionStateDelay;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.ShooterState;

public class CargoShoot extends FunctionBase implements FunctionInterface{

    long originalTime = 0;

    public CargoShoot(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!running && Buttons.CargoShoot.getButton()){

            this.reset();

        }

        switch(this.state) {

        case 0:

            if((Buttons.CargoShoot.getButton() && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && !Robot.functionProcessor.cargoIntakeFloor.isRunning()) {
                
                this.started();
                this.state = 10;
                
            }

            break;

        case 10:

            originalTime = System.currentTimeMillis();
            Robot.shooter.setShooterState(ShooterState.SHOOT);
            this.state = 20;
            
            break;

        case 20:

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.cargoShoot_shooterShoot_to_shooterStop.time){

                this.state = 30;

            }

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