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

    private Solenoid leftExtend = RobotMap.leftArmExtendCylinder;
    private Solenoid rightExtend = RobotMap.rightArmExtendCylinder;

    private ArmExtendState armExtendState = ArmExtendState.COLLAPSED;
    private ArmLiftState armLiftState = ArmLiftState.COLLAPSED;
    private RollerState rollerState = RollerState.STOP;

    public Intake(){

    }

    @Override
    public void process(){

        switch(armExtendState){

        case EXTENDED:

            setArmExtendCylindersExtended();

            break;

        case COLLAPSED:

            setArmExtendCylindersCollapsed();

            break;

        }

        switch(armLiftState){

        case COLLAPSED:

            setShortArmCylindersCollapsed();
            setLongArmCylindersCollapsed();

            break;

        case MID:

            setShortArmCylindersExtended();
            setLongArmCylindersCollapsed();

            break;

        case UP:

            setShortArmCylindersExtended();
            setLongArmCylindersExtended();

            break;

        }

        switch(rollerState){

        case INTAKE:

            cargoRoller.set(0.5);

            break;

        case STOP:

            cargoShooter.set(0.0);

            break;

        }
    }

    public enum CylinderState{

        EXTENDED,
        COLLAPSED

    }

    public enum ArmExtendState{

        EXTENDED,
        COLLAPSED;

    }

    public enum ArmLiftState{

        COLLAPSED,
        MID,
        UP;

    }

    public enum RollerState{

        INTAKE,
        STOP;

    }

    public void setArmExtendState(ArmExtendState state){

        armExtendState = state;

    }

    public void setArmLiftState(ArmLiftState state){

        armLiftState = state;

    }

    public void setRollerState(RollerState state){

        rollerState = state;

    }

    public ArmExtendState getArmExtendState(){

        return armExtendState;

    }

    public ArmLiftState getArmLiftState(){

        return armLiftState;

    }

    public RollerState getRollerState(){

        return rollerState;

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

    private void setArmExtendCylindersExtended(){

        leftExtend.set(true);
        rightExtend.set(true);

    }

    private void setArmExtendCylindersCollapsed(){

        leftExtend.set(false);
        rightExtend.set(false);

    }

    public boolean isArmAft(){

        return RobotMap.forwardAftSensor.get();

    }
}