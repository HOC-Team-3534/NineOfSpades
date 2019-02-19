package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
/**
 * The Climber subsystem is used to control the pnuematic cylinders that are depended
 * on for the endgame climbing. In this subsystem the control of the pnuematic cylinders
 * is given to the Y button on the Driver's controller, which has to be held down for the
 * duration of the climb.
 */
public class Climber extends SystemBase implements SystemInterface{

    private DoubleSolenoid solenoid1 = RobotMap.climbingCylinderOne; //These two lines are used to create variables of type DoubleSolenoid
    private DoubleSolenoid solenoid2 = RobotMap.climbingCylinderTwo;

    CylinderState cylinder1 = CylinderState.COLLAPSED; //These two lines are used to create variables of type STATE
    CylinderState cylinder2 = CylinderState.COLLAPSED;

    ClimberState climberState = ClimberState.NULL;

    public Climber(){} //The default constructor of the Climber class

    @Override
    public void process(){

        switch(climberState){
        case Climb: //Should the driver hold the Y button, the cylinders will extend due to air being put to them.
            
            setCylinder1Extended();
            setCylinder2Extended();
            cylinder1 = CylinderState.EXTENDED;
            cylinder2 = CylinderState.EXTENDED;
            break;

        case Retract: //Once the Y button is released by the driver, the cylinders will depress due to the air being put to them in the opposite direction of the initial force of the air.

            setCylinder1Collapsed();
            setCylinder2Collapsed();
            cylinder1 = CylinderState.COLLAPSED;
            cylinder2 = CylinderState.COLLAPSED;
            break;

        case NULL:

            setCylindersOff();

            break;
            
        }

    }

    private enum CylinderState{ //This method is used to describe all possible states of the cylinders

        EXTENDED,
        COLLAPSED;

    }

    public enum ClimberState{
        Climb,
        Retract,
        NULL
    }

    public void setClimberState(ClimberState state) {

        climberState = state;

    }

    private void setCylinder1Extended() {

        //give power to solenoid1 bottom
        solenoid1.set(Value.kForward);

    }

    private void setCylinder1Collapsed() {

        //give power to solenoid1 top
        solenoid1.set(Value.kReverse);

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

}