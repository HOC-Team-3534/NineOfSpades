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

        //setting the DPAD buttons up so we dont have to type Robot.oi.getController2().getPOVValue(POV.Direction)
        DPAD_DOWN = Robot.oi.getController2().getPOVValue(POV.South);
        DPAD_UP = Robot.oi.getController2().getPOVValue(POV.North);
        DPAD_RIGHT = Robot.oi.getController2().getPOVValue(POV.East);

    }

    @Override
    public void process() {

        if(Robot.oi.getController2().getPOV() == DPAD_DOWN) {//STAGE 1

            if (cylinder1 == STATE.EXTENDED) {//if the first cylinder is extended

                if (limitSwitch.get()) {//if the first cylinder is at the limit switch

                    limitSwitchMet = true;

                }
                if (!limitSwitchMet) {//if the limit switch is not met

                    setCylinder1Collapsed();

                } else {//if the first cylinder is already at stage 1

                    //STAY THERE??????????

                }

            } else if (cylinder1 == STATE.COLLAPSED) {//if the first cylinder is collapsed

                if (limitSwitch.get()) {//if the first cylinder is at the limit switch

                    limitSwitchMet = true;

                }
                if (!limitSwitchMet) {//if the limit switch is not met

                   setCylinder1Extended();

                } else {//if the first cylinder is already at stage 1

                    //STAY THERE??????????

                }

            } else {//if the first cylinder is already at stage 1

                //nothing

            }
            if (cylinder2 == STATE.EXTENDED) {//if the second cylinder is extended

                setCylinder2Collapsed();

            } else {//if the second cylinder is collapsed

                //nothing

            }

        } else if (Robot.oi.getController2().getPOV() == DPAD_RIGHT) {//STAGE 2

            if (cylinder1 != STATE.EXTENDED) {//if the first cylinder is not extended

                setCylinder1Extended();

            } else {//if the first cylinder is at stage 2

                //nothing

            }
            if (cylinder2 == STATE.EXTENDED) {//if the second cylinder is extended

                setCylinder2Collapsed();

            } else {//if the second cylinder is collapsed

                //nothing

            } 

        } else if(Robot.oi.getController2().getPOV() == DPAD_UP) {//STAGE 3

            if(cylinder2 != STATE.EXTENDED) {//if the second cylinder is collapsed

                setCylinder2Extended();

            } else {//if the second cylinder is extended

                //nothing

            }
            if(cylinder1 != STATE.EXTENDED) {//if the first cylinder is not extended

                setCylinder1Extended();

            } else {//if the first cylinder is extended

                //nothing

            }

        } else {//bottom stage, DPAD not pressed

            if(cylinder1 != STATE.COLLAPSED) {//if the first cylinder is not extended

                setCylinder1Collapsed();

            } else {//if the first cylinder is extended

                //nothing

            }

            if(cylinder2 != STATE.COLLAPSED) {//if the second cylinder is extended

                setCylinder2Collapsed();

            } else {//if the second cylinder is collapsed

                //nothing

            }

        }

    }

    private enum STATE{

        EXTENDED,
        COLLAPSED,
        HALFWAY //ONLY FOR CYLINDER 1

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

