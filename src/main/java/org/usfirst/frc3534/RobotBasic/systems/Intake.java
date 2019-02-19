package org.usfirst.frc3534.RobotBasic.systems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake extends SystemBase implements SystemInterface{

    private WPI_TalonSRX cargoRoller = RobotMap.cargoRoller;

    private DoubleSolenoid shortCylinders = RobotMap.intakeDownCylinders;
    private DoubleSolenoid longCylinders = RobotMap.intakeUpCylinders;

    private DoubleSolenoid forwardAftCylinders = RobotMap.intakeForwardAftCylinders;

    private ArmExtendState armExtendState = ArmExtendState.NULL;
    private ArmLiftState armLiftState = ArmLiftState.NULL;
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

        case NULL:

            setArmExtendCylindersOff();

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

        case NULL:

            setShortArmCylindersOff();
            setLongArmCylindersOff();

            break;

        }

        switch(rollerState){

        case INTAKE:

            cargoRoller.set(0.5);

            break;

        case STOP:

            cargoRoller.set(0.0);

            break;

        }
    }

    public enum CylinderState{

        EXTENDED,
        COLLAPSED

    }

    public enum ArmExtendState{

        EXTENDED,
        COLLAPSED,
        NULL

    }

    public enum ArmLiftState{

        COLLAPSED,
        MID,
        UP,
        NULL

    }

    public enum RollerState{

        INTAKE,
        STOP

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

        shortCylinders.set(Value.kForward);

    }

    private void setShortArmCylindersCollapsed(){

        shortCylinders.set(Value.kReverse);

    }

    private void setShortArmCylindersOff(){

        shortCylinders.set(Value.kOff);

    }

    private void setLongArmCylindersExtended(){

        longCylinders.set(Value.kForward);

    }

    private void setLongArmCylindersCollapsed(){

        longCylinders.set(Value.kReverse);

    }

    private void setLongArmCylindersOff(){

        longCylinders.set(Value.kOff);

    }

    private void setArmExtendCylindersExtended(){

        forwardAftCylinders.set(Value.kForward);

    }

    private void setArmExtendCylindersCollapsed(){

        forwardAftCylinders.set(Value.kReverse);

    }

    private void setArmExtendCylindersOff(){

        forwardAftCylinders.set(Value.kOff);

    }

    public boolean isArmAft(){

        return RobotMap.forwardAftSensor.get();

    }
}