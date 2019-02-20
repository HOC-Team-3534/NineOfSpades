package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.ShooterState;

import edu.wpi.first.wpilibj.GenericHID.Hand;

public class CargoShoot extends FunctionBase implements FunctionInterface{

    long originalTime = 0;

    public CargoShoot(){

        reset();
        completed();

    }

    @Override
    public void process(){

        if(!running && Robot.oi.getController2().getBumper(Hand.kRight)){

            this.reset();

        }

        switch(this.state) {

        case 0:

            if((Robot.oi.getController2().getBumper(Hand.kRight) && (!Robot.functionProcessor.cargoIntakeTop.isRunning() && !Robot.functionProcessor.hatchPlace.isRunning())) && ((!Robot.functionProcessor.cargoIntakeFloor.isRunning() && !Robot.functionProcessor.habLevel3ClimbPart1.isRunning()) && (!Robot.functionProcessor.habLevel3ClimbPart2.isRunning() && !Robot.functionProcessor.xButtonReset.isRunning()))) {
                
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

            if(System.currentTimeMillis() - originalTime > 3.0 * 1000){

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