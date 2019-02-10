package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;

public class HatchPanelApparatus extends SystemBase implements SystemInterface{

    private HatchPanelApparatusState hatchPanelApparatusState = HatchPanelApparatusState.COLLAPSED;

    private Solenoid cylinder1 = RobotMap.panelCylinder1;
    private Solenoid cylinder2 = RobotMap.panelCylinder2;
    private Solenoid cylinder3 = RobotMap.panelCylinder3;

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

        }

    }

    public enum CylinderState{

        COLLAPSED,
        EXTENDED

    }

    public enum HatchPanelApparatusState{

        EXTENDED,
        COLLAPSED

    }

    public void setHatchPanelApparatusState(HatchPanelApparatusState state){

        hatchPanelApparatusState = state;

    }

    private void setHatchPanelCylindersExtended(){

        cylinder1.set(true);
        cylinder1.set(true);
        cylinder3.set(true);

    }

    private void setHatchPanelCylindersCollapsed(){

        cylinder1.set(false);
        cylinder2.set(false);
        cylinder3.set(false);

    }

}