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
        
        INTAKE(RobotMap.PowerOutput.shooter_shooter_intake.power),
        SHOOT(RobotMap.PowerOutput.shooter_shooter_shoot.power),
        STOP(RobotMap.PowerOutput.shooter_shooter_stop.power);

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