package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.XboxPlusPOV;
import org.usfirst.frc3534.RobotBasic.XboxPlusPOV.POV;

import org.usfirst.frc3534.RobotBasic.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class Elevator extends SystemBase implements SystemInterface {

    private boolean limitSwitchMet = false;

    private Solenoid solenoid1 = RobotMap.elevatorCylinderOne;
    private Solenoid solenoid2 = RobotMap.elevatorCylinderTwo;

    private DigitalInput limitSwitch = RobotMap.limitSwitch;

    private final int DPAD_DOWN;
    private final int DPAD_UP;
    private final int DPAD_RIGHT;

    STATE cylinder1 = STATE.COLLAPSED;
    STATE cylinder2 = STATE.COLLAPSED;

    public Elevator() {

        //setting the DPAD buttons up so we dont have to type XboxPlusPOV.POV.Direction.getValue()
        DPAD_DOWN = XboxPlusPOV.POV.South.getValue();
        DPAD_UP = XboxPlusPOV.POV.North.getValue();
        DPAD_RIGHT = XboxPlusPOV.POV.East.getValue();

    }

    @Override
    public void process() {

        if(Robot.oi.getController2().getPOV() == DPAD_DOWN) {//DPAD DOWN, STAGE 1A

            if (cylinder1 == STATE.EXTENDED) {//if the first cylinder is extended

                if (limitSwitch.get()) {//if the first cylinder is at the limit switch

                    limitSwitchMet = true;
                    cylinder1 = STATE.HALFWAY;

                }
                if (!limitSwitchMet) {//if the limit switch is not met

                    setCylinder1Collapsed();
                    setCylinder2Collapsed();

                } else {//if the first cylinder is already at stage 1

                    //STAY THERE??????????

                }

            } else if (cylinder1 == STATE.COLLAPSED) {//if the first cylinder is collapsed

                if (limitSwitch.get()) {//if the first cylinder is at the limit switch

                    limitSwitchMet = true;
                    cylinder1 = STATE.HALFWAY;

                }
                if (!limitSwitchMet) {//if the limit switch is not met

                   setCylinder1Extended();
                   setCylinder2Collapsed();


                } else {//if the first cylinder is already at stage 1

                    //STAY THERE??????????

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

        } else {//bottom stage, DPAD not pressed

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

        EXTENDED(1),
        COLLAPSED(2),
        HALFWAY(3); //ONLY FOR CYLINDER 1

        int value;

        private STATE(int value){

            this.value = value;

        }

        private int getValue(){

            return value;

        }

    }

    //solenoid_.set(true) pushes air through the bottom
    //solenoid_.set(false) pushes air through the top

    private void setCylinder1Extended() {

        //give power to solenoid1 bottom
        solenoid1.set(true);

    }

    private void setCylinder1Collapsed() {

        //give power to solenoid1 top
        solenoid1.set(false);

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

