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

    private long originalTimeArmExtend = 0l;
    private long originalTimeArmLift = 0l;

    public Intake(){

    }

    @Override
    public void process(){

        switch(armExtendState){

        case EXTENDED:

            setArmExtendCylindersExtended();

            if(System.currentTimeMillis() - originalTimeArmExtend > 3 * 1000){

                armExtendState = ArmExtendState.OFF;

            }

            break;

        case COLLAPSED:

            setArmExtendCylindersCollapsed();

            if(isArmAft()){

                armExtendState = ArmExtendState.OFF;

            }

            break;

        case OFF:

            setArmExtendCylindersOff();

            break;

        case NULL:

            setArmExtendCylindersOff();

            break;

        }

        switch(armLiftState){

        case COLLAPSED:

            setShortArmCylindersCollapsed();
            setLongArmCylindersCollapsed();

            if(System.currentTimeMillis() - originalTimeArmLift > 3 * 1000){

                armLiftState = ArmLiftState.OFF;

            }

            break;

        case MID:

            setShortArmCylindersExtended();
            setLongArmCylindersCollapsed();

            if(System.currentTimeMillis() - originalTimeArmLift > 3 * 1000){

                armLiftState = ArmLiftState.OFF;

            }

            break;

        case UP:

            setShortArmCylindersExtended();
            setLongArmCylindersExtended();

            if(System.currentTimeMillis() - originalTimeArmLift > 3 * 1000){

                armLiftState = ArmLiftState.OFF;

            }

            break;

        case OFF:

            setShortArmCylindersOff();
            setLongArmCylindersOff();

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
        OFF,
        NULL

    }

    public enum ArmLiftState{

        COLLAPSED,
        MID,
        UP,
        OFF,
        NULL

    }

    public enum RollerState{

        INTAKE,
        STOP

    }

    public void setArmExtendState(ArmExtendState state){

        armExtendState = state;
        originalTimeArmExtend = System.currentTimeMillis();

    }

    public void setArmLiftState(ArmLiftState state){

        armLiftState = state;
        originalTimeArmLift = System.currentTimeMillis();

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

        return !RobotMap.forwardAftSensor.get();

    }
}