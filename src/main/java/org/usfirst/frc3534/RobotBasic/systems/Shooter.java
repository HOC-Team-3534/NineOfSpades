package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class Shooter extends SystemBase implements SystemInterface{

    SpeedControllerGroup shooter = RobotMap.cargoShooter;

    ShooterState shooterState = ShooterState.STOP;

    public Shooter(){

    }

    @Override
    public void process(){

        switch(shooterState){
        case INTAKE:

            setShooterPower(shooterState.value);    

            break;

        case SHOOT:

            setShooterPower(shooterState.value); 

            break;

        case STOP:

            setShooterPower(shooterState.value); 

            break;

        }

    }

    public enum ShooterState{
        
        INTAKE(-.3),
        SHOOT(.4),
        STOP(0);

        double value;

        private ShooterState(double value){

            this.value = value;

        }

    }

    public void setShooterState(ShooterState state){

        shooterState = state;

    }

    private void setShooterPower(double power){

        shooter.set(power);

    }

}