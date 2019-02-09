package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.Robot;

import edu.wpi.first.wpilibj.Solenoid;
/**
 * The Climber subsystem is used to control the pnuematic cylinders that are depended
 * on for the endgame climbing. In this subsystem the control of the pnuematic cylinders
 * is given to the Y button on the Driver's controller, which has to be held down for the
 * duration of the climb.
 */
public class Climber extends SystemBase implements SystemInterface{

    private Solenoid solenoid1 = RobotMap.climbingCylinderOne; //These two lines are used to create variables of type Solenoid
    private Solenoid solenoid2 = RobotMap.climbingCylinderTwo;

    public Climber(){} //The default constructor of the Climber class

    public void process(){

        if(Robot.oi.getController1().getYButton()){ //Should the driver hold the Y button, the cylinders will extend due to air being put to them.
            
            setCylinder1Extended();
            setCylinder2Extended();
            cylinder1 = STATE.EXTENDED;
            cylinder2 = STATE.EXTENDED;

        }else{ //Once the Y button is released by the driver, the cylinders will depress due to the air being put to them in the opposite direction of the initial force of the air.

            setCylinder1Collapsed();
            setCylinder2Collapsed();
            cylinder1 = STATE.COLLAPSED;
            cylinder2 = STATE.COLLAPSED;

        }

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