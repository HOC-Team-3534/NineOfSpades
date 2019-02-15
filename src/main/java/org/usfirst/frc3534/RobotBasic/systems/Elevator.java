package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Elevator extends SystemBase implements SystemInterface {

    private boolean limitSwitchMet = false;

    private DoubleSolenoid solenoid1 = RobotMap.elevatorCylinderOne;
    private Solenoid solenoid2 = RobotMap.elevatorCylinderTwo;

    private DigitalInput limitSwitch = RobotMap.limitSwitch;

    CylinderState cylinder1 = CylinderState.COLLAPSED;
    CylinderState cylinder2 = CylinderState.COLLAPSED;

    ElevatorState elevatorState = ElevatorState.Floor;

    public Elevator() {

    }

    @Override
    public void process() {

        switch(elevatorState){

        case Stage1A:

            if (cylinder1 == CylinderState.EXTENDED) {//if the first cylinder is extended

                if (limitSwitch.get()) {//if the first cylinder is at the limit switch

                    limitSwitchMet = true;
                    cylinder1 = CylinderState.HALFWAY;

                }
                if (!limitSwitchMet) {//if the limit switch is not met

                    setCylinder1Collapsed();
                    setCylinder2Collapsed();

                } else {//if the first cylinder is already at stage 1

                    //STAY THERE
                    setCylinder1Halfway();

                }

            } else if (cylinder1 == CylinderState.COLLAPSED) {//if the first cylinder is collapsed

                if (limitSwitch.get()) {//if the first cylinder is at the limit switch

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

            break;

        case Stage1B:

            setCylinder1Extended();
            setCylinder2Collapsed();
            if(cylinder1 == CylinderState.COLLAPSED){

                if(limitSwitch.get()){

                    cylinder1 = CylinderState.EXTENDED;

                }

            }else{

                cylinder1 = CylinderState.EXTENDED;

            }
            cylinder2 = CylinderState.COLLAPSED;

            break;

        case Stage2:

            setCylinder1Extended();
            setCylinder2Extended();
            if(cylinder1 == CylinderState.COLLAPSED){//if the first cylinder is collapsed 

                if(limitSwitch.get()){//if the cylinder is at the limit switch

                    cylinder1 = CylinderState.EXTENDED;

                }

            }else{//if the first cylinder is not collapsed

                cylinder1 = CylinderState.EXTENDED;

            }
            cylinder2 = CylinderState.EXTENDED;

            break;

        case Floor:

            setCylinder1Collapsed();
            setCylinder2Collapsed();
            if(cylinder1 == CylinderState.EXTENDED){//if the first cylinder is extended

                if(limitSwitch.get()){//if the cylinder is at the limit switch

                    cylinder1 = CylinderState.COLLAPSED;

                }

            }else{//if the first cylinder is not extended

                cylinder1 = CylinderState.COLLAPSED;

            }

            break;

        }

    }

    private enum CylinderState{

        EXTENDED,
        COLLAPSED,
        HALFWAY; //ONLY FOR CYLINDER 1

    }

    public enum ElevatorState{

        Floor(1),
        Stage1A(2),
        Stage1B(3),
        Stage2(4);

        int value;

        private ElevatorState(int value){

            this.value = value;

        }

    }

    public void setElevatorState(ElevatorState state){

        elevatorState = state;

    }

    private ElevatorState getElevatorState(){

        return elevatorState;

    }

    //solenoid_.set(true) pushes air through the bottom
    //solenoid_.set(false) pushes air through the top

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
        solenoid2.set(true);

    }

    private void setCylinder2Collapsed() {

        //give power to solenoid2 top
        solenoid2.set(false);

    }

}

