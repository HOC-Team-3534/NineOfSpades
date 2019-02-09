package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.XboxPlusPOV.POV;

import org.usfirst.frc3534.RobotBasic.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Elevator extends SystemBase implements SystemInterface {

    private boolean limitSwitchMet = false;

    private DoubleSolenoid solenoid1 = RobotMap.elevatorCylinderOne;
    private Solenoid solenoid2 = RobotMap.elevatorCylinderTwo;

    private DigitalInput limitSwitch = RobotMap.limitSwitch;

    private final int DPAD_DOWN;
    private final int DPAD_UP;
    private final int DPAD_RIGHT;

    STATE cylinder1 = STATE.COLLAPSED;
    STATE cylinder2 = STATE.COLLAPSED;

    public Elevator() {

        DPAD_DOWN = POV.South.getValue();
        DPAD_UP = POV.North.getValue();
        DPAD_RIGHT = POV.East.getValue();

    }

    @Override
    public void process() {

        if(Robot.oi.getController2().getPOV() == DPAD_DOWN) {//DPAD DOWN, STAGE 1A

            if (cylinder1 == STATE.EXTENDED) {

                if (limitSwitch.get()) {

                    limitSwitchMet = true;
                    cylinder1 = STATE.HALFWAY;

                }
                if (!limitSwitchMet) {

                    setCylinder1Collapsed();
                    setCylinder2Collapsed();

                } else {

                    //STAY THERE
                    setCylinder1Halfway();

                }

            } else if (cylinder1 == STATE.COLLAPSED) {

                if (limitSwitch.get()) {

                    limitSwitchMet = true;
                    cylinder1 = STATE.HALFWAY;

                }
                if (!limitSwitchMet) {

                   setCylinder1Extended();
                   setCylinder2Collapsed();


                } else {

                    //STAY THERE
                    setCylinder1Halfway();

                }

            } 

        } else if (Robot.oi.getController2().getPOV() == DPAD_RIGHT) {//DPAD RIGHT, STAGE 1B

            setCylinder1Extended();
            setCylinder2Collapsed();
            if(cylinder1 == STATE.COLLAPSED){

                if(limitSwitch.get()){

                    cylinder1 = STATE.EXTENDED;

                }

            }else{

                cylinder1 = STATE.EXTENDED;

            }
            cylinder2 = STATE.COLLAPSED;

        } else if(Robot.oi.getController2().getPOV() == DPAD_UP) {//DPAD UP, STAGE 2

            setCylinder1Extended();
            setCylinder2Extended();
            if(cylinder1 == STATE.COLLAPSED){

                if(limitSwitch.get()){

                    cylinder1 = STATE.EXTENDED;

                }

            }else{

                cylinder1 = STATE.EXTENDED;

            }
            cylinder2 = STATE.EXTENDED;

        } else {

            setCylinder1Collapsed();
            setCylinder2Collapsed();
            if(cylinder1 == STATE.EXTENDED){

                if(limitSwitch.get()){

                    cylinder1 = STATE.COLLAPSED;

                }

            }else{

                cylinder1 = STATE.COLLAPSED;

            }

        }

    }

    private enum STATE{

        EXTENDED,
        COLLAPSED,
        HALFWAY; //ONLY FOR CYLINDER 1

    }

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

