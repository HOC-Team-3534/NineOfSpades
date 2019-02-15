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
                if(Robot.oi.getController2().getBumper(Hand.kRight)) {
                    this.started();
                    this.state = 10;
                    originalTime = System.currentTimeMillis();
                }
                break;
    
            case 10:

                Robot.shooter.setShooterState(ShooterState.SHOOT);

                if(System.currentTimeMillis() - originalTime > .75 * 1000){

                    this.state = 20;

                }
                
                break;

            case 20:

                Robot.shooter.setShooterState(ShooterState.STOP);
                this.state = 30;

                break;

            case 30:

                completed();

                break;
    
            }

    }

}