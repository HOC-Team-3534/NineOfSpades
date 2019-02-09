package org.usfirst.frc3534.RobotBasic.systems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class Intake extends SystemBase implements SystemInterface{

    private SpeedControllerGroup cargoShooter = RobotMap.cargoShooter;
    private WPI_TalonSRX cargoRoller = RobotMap.cargoRoller;

    private Solenoid leftShort = RobotMap.leftShortArmCylinder;
    private Solenoid leftLong = RobotMap.leftLongArmCylinder;
    private Solenoid rightShort = RobotMap.rightShortArmCylinder;
    private Solenoid rightLong = RobotMap.rightLongArmCylinder;

    public Intake(){

    }

    @Override
    public void process(){

    }

    public enum STATE{

        EXTENDED,
        COLLAPSED

    }

    private void setShortArmCylindersExtended(){

        leftShort.set(true);
        rightShort.set(true);

    }

    private void setShortArmCylindersCollapsed(){

        leftShort.set(false);
        rightShort.set(false);

    }

    private void setLongArmCylindersExtended(){

        leftLong.set(true);
        rightLong.set(true);

    }

    private void setLongArmCylindersCollapsed(){

        leftLong.set(false);
        rightLong.set(false);

    }

}