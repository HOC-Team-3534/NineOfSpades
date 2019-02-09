package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.Robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Climber extends SystemBase implements SystemInterface{

    private Solenoid solenoid1 = RobotMap.climbingCylinderOne;
    private Solenoid solenoid2 = RobotMap.climbingCylinderTwo;

    STATE cylinder1 = STATE.COLLAPSED;
    STATE cylinder2 = STATE.COLLAPSED;

    public Climber(){}

    public void process(){

        if(Robot.oi.getController1().getYButton()){
            
            setCylinder1Extended();
            setCylinder2Extended();
            cylinder1 = STATE.EXTENDED;
            cylinder2 = STATE.EXTENDED;

        }else{

            setCylinder1Collapsed();
            setCylinder2Collapsed();
            cylinder1 = STATE.COLLAPSED;
            cylinder2 = STATE.COLLAPSED;

        }

    }

    private enum STATE{

        EXTENDED,
        COLLAPSED

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