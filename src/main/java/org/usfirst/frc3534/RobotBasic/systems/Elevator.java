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

        DPAD_DOWN = Robot.oi.getController2().getPOVValue(POV.South);
        DPAD_UP = Robot.oi.getController2().getPOVValue(POV.North);
        DPAD_RIGHT = Robot.oi.getController2().getPOVValue(POV.East);

    }

    @Override
    public void process() {

        if(Robot.oi.getController2().getPOV() == DPAD_DOWN) {//DPAD DOWN, STAGE 1

            if (cylinder1 == STATE.EXTENDED) {

                if (limitSwitch.get()) {

                    limitSwitchMet = true;

                }
                if (!limitSwitchMet) {

                    setCylinder1Collapsed();

                } else {

                    //STAY THERE??????????

                }

            } else if (cylinder1 == STATE.COLLAPSED) {

                if (limitSwitch.get()) {

                    limitSwitchMet = true;

                }
                if (!limitSwitchMet) {

                   setCylinder1Extended();

                } else {

                    //STAY THERE??????????

                }

            } else {

                //nothing

            }

        } else if (Robot.oi.getController2().getPOV() == DPAD_RIGHT) {//DPAD RIGHT, STAGE 2

            if (cylinder1 != STATE.EXTENDED) {

                setCylinder1Extended();

            } else {

                //nothing

            }

        } else if(Robot.oi.getController2().getPOV() == DPAD_UP) {//DPAD UP, STAGE 3

            if(cylinder2 != STATE.EXTENDED) {

                setCylinder2Extended();

            } else {

                //nothing

            }

        } else {

            if(cylinder1 != STATE.COLLAPSED) {

                setCylinder1Collapsed();

            } else {

                //nothing

            }

            if(cylinder2 != STATE.COLLAPSED) {

                setCylinder2Collapsed();

            } else {

                //nothing

            }

        }

    }

    private enum STATE{

        EXTENDED,
        COLLAPSED,
        HALFWAY //ONLY FOR CYLINDER 1

    }

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

