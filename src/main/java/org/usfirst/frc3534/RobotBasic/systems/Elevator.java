package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.RobotMap.DelayToOff;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends SystemBase implements SystemInterface {

    private boolean limitSwitchMet = false;

    private DoubleSolenoid solenoid1 = RobotMap.elevatorCylinderOne;
    private DoubleSolenoid solenoid2 = RobotMap.elevatorCylinderTwo;

    private DigitalInput limitSwitch = RobotMap.limitSwitch;
    private Counter switchCounter = new Counter(limitSwitch);

    CylinderState cylinder1 = CylinderState.COLLAPSED;
    CylinderState cylinder2 = CylinderState.COLLAPSED;

    ElevatorState elevatorState = ElevatorState.NULL;

    private long originalTimeElevator = 0l;

    public Elevator() {

    }

    @Override
    public void process() {

        SmartDashboard.putBoolean("Stage 1A", getElevatorSwitchCounter());

        switch(elevatorState){

        case Stage1A:

            if (cylinder1 == CylinderState.EXTENDED) {//if the first cylinder is extended

                /*
                if (getElevatorSwitchCounter()) {//if the first cylinder is at the limit switch

                    limitSwitchMet = true;
                    cylinder1 = CylinderState.HALFWAY;

                }
                if (!limitSwitchMet) {//if the limit switch is not met

                    setCylinder1Collapsed();
                    setCylinder2Collapsed();

                } else {//if the first cylinder is already at stage 1

                    //STAY THERE
                    setCylinder1Halfway();

                }*/

            } else if (cylinder1 == CylinderState.COLLAPSED) {//if the first cylinder is collapsed

                if (getElevatorSwitchCounter()) {//if the first cylinder is at the limit switch

                    limitSwitchMet = true;
                    cylinder1 = CylinderState.HALFWAY;

                }
                if (!limitSwitchMet) {//if the limit switch is not met

                   setCylinder1Extended();
                   setCylinder2Collapsed();


                } else {//if the first cylinder is already at stage 1

                    //STAY THERE
                    setCylinder1Halfway();

                }

            } 

            if(System.currentTimeMillis() - originalTimeElevator > DelayToOff.elevator_stage1a.time){

                elevatorState = ElevatorState.OFF;

            }

            break;

        case Stage1B:

            setCylinder1Extended();
            setCylinder2Collapsed();
            if(cylinder1 == CylinderState.COLLAPSED){

                if(getElevatorSwitchCounter()){

                    cylinder1 = CylinderState.EXTENDED;

                }

            }else{

                cylinder1 = CylinderState.EXTENDED;

            }
            cylinder2 = CylinderState.COLLAPSED;

            if(System.currentTimeMillis() - originalTimeElevator > DelayToOff.elevator_stage1b.time){

                elevatorState = ElevatorState.OFF;

            }

            break;

        case Stage2:

            setCylinder1Extended();
            setCylinder2Extended();
            if(cylinder1 == CylinderState.COLLAPSED){//if the first cylinder is collapsed 

                if(getElevatorSwitchCounter()){//if the cylinder is at the limit switch

                    cylinder1 = CylinderState.EXTENDED;

                }

            }else{//if the first cylinder is not collapsed

                cylinder1 = CylinderState.EXTENDED;

            }
            cylinder2 = CylinderState.EXTENDED;

            if(System.currentTimeMillis() - originalTimeElevator > DelayToOff.elevator_stage2.time){

                elevatorState = ElevatorState.OFF;

            }

            break;

        case Floor:

            limitSwitchMet = false;
            setCylinder1Collapsed();
            setCylinder2Collapsed();
            if(cylinder1 == CylinderState.EXTENDED){//if the first cylinder is extended

                //if(getElevatorSwitchCounter()){//if the cylinder is at the limit switch

                    cylinder1 = CylinderState.COLLAPSED;

                //}

            }else{//if the first cylinder is not extended

                cylinder1 = CylinderState.COLLAPSED;

            }

            if(System.currentTimeMillis() - originalTimeElevator > DelayToOff.elevator_floor.time){

                elevatorState = ElevatorState.OFF;

            }

            break;

        case OFF:

            setCylindersOff();

            break;

        case NULL:

            setCylindersOff();

            break;

        }

        setSwitchCounterReset();

    }

    private enum CylinderState{

        EXTENDED,
        COLLAPSED,
        HALFWAY; //ONLY FOR CYLINDER 1

    }

    public enum ElevatorState{

        Floor,
        Stage1A,
        Stage1B,
        Stage2,
        OFF,
        NULL

    }

    public void setElevatorState(ElevatorState state){

        elevatorState = state;
        originalTimeElevator = System.currentTimeMillis();

    }

    public ElevatorState getElevatorState(){

        return elevatorState;

    }

    //solenoid_.set(Value.kForward) pushes air through the bottom
    //solenoid_.set(Value.kReverse) pushes air through the top
    //solenoid_.set(Value.kOff) puts the value in the center position

    private void setCylinder1Extended() {

        //give power to solenoid1 bottom
        solenoid1.set(Value.kForward);

    }

    private void setCylinder1Collapsed() {

        //give power to solenoid1 top (in theory). Use gravity and plug the value
        solenoid1.set(Value.kReverse);

    }

    private void setCylinder1Halfway(){

        //block all pathways to the atmosphere
        solenoid1.set(Value.kOff);

    }

    private void setCylinder2Extended() {

        //give power to solenoid2 bottom
        solenoid2.set(Value.kForward);

    }

    private void setCylinder2Collapsed() {

        //give power to solenoid2 top
        solenoid2.set(Value.kReverse);

    }

    private void setCylindersOff(){

        solenoid1.set(Value.kOff);
        solenoid2.set(Value.kOff);

    }

    private boolean getElevatorSwitchCounter(){

        return switchCounter.get() > 0;

    }

    private void setSwitchCounterReset(){

        switchCounter.reset();

    }

}

