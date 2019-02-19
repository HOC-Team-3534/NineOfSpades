package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class HatchPanelApparatus extends SystemBase implements SystemInterface{

    private HatchPanelApparatusState hatchPanelApparatusState = HatchPanelApparatusState.NULL;

    private DoubleSolenoid cylinders = RobotMap.panelCylinders;

    public HatchPanelApparatus(){

    }

    @Override
    public void process(){

        switch(hatchPanelApparatusState){

        case EXTENDED:

            setHatchPanelCylindersExtended();

            break;

        case COLLAPSED:

            setHatchPanelCylindersCollapsed();

            break;

        case NULL:

            setHatchPanelCylindersOff();

            break;

        }

    }

    public enum CylinderState{

        COLLAPSED,
        EXTENDED

    }

    public enum HatchPanelApparatusState{

        EXTENDED,
        COLLAPSED,
        NULL

    }

    public void setHatchPanelApparatusState(HatchPanelApparatusState state){

        hatchPanelApparatusState = state;

    }

    private void setHatchPanelCylindersExtended(){

        cylinders.set(Value.kForward);

    }

    private void setHatchPanelCylindersCollapsed(){

        cylinders.set(Value.kReverse);

    }

    private void setHatchPanelCylindersOff(){

        cylinders.set(Value.kOff);

    }

}