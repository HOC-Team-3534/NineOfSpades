package org.usfirst.frc3534.RobotBasic.systems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends SystemBase implements SystemInterface{

    private WPI_TalonSRX cargoRoller = RobotMap.cargoRoller;

    private DoubleSolenoid forwardAftCylinders = RobotMap.intakeForwardAftCylinders;

    private DigitalInput rightArmSwitch = RobotMap.rightArmSensor;
    private DigitalInput leftArmSwitch = RobotMap.leftArmSensor;
    private Counter rightArmCounter = new Counter(rightArmSwitch);
    private Counter leftArmCounter = new Counter(leftArmSwitch);

    private ArmExtendState armExtendState = ArmExtendState.NULL;
    private RollerState rollerState = RollerState.STOP;

    private long originalTimeArmExtend = 0l;

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

        switch(rollerState){

        case INTAKE:

            cargoRoller.set(RollerState.INTAKE.value);

            break;

        case STOP:

            cargoRoller.set(RollerState.STOP.value);

            break;

        }

        setArmCountersReset();

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

    public enum RollerState{

        INTAKE(RobotMap.PowerOutput.intake_roller_intake.power),
        STOP(RobotMap.PowerOutput.intake_roller_stop.power);

        double value;

        private RollerState(double value){

            this.value = value;

        }

    }

    public void setArmExtendState(ArmExtendState state){

        armExtendState = state;
        originalTimeArmExtend = System.currentTimeMillis();

    }

    public void setRollerState(RollerState state){

        rollerState = state;

    }

    public ArmExtendState getArmExtendState(){

        return armExtendState;

    }

    public RollerState getRollerState(){

        return rollerState;

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

        return rightArmCounter.get() > 0 || leftArmCounter.get() > 0;

    }

    public void setArmCountersReset(){

        rightArmCounter.reset();
        leftArmCounter.reset();

    }
}