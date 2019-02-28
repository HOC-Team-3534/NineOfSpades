package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class HatchPanelApparatus extends SystemBase implements SystemInterface{

    private HatchPanelApparatusState hatchPanelApparatusState = HatchPanelApparatusState.NULL;
    private HatchIntakeState hatchIntakeState = HatchIntakeState.NULL;

    private DoubleSolenoid cylinders = RobotMap.panelCylinders;
    private DoubleSolenoid intakeCylinders = RobotMap.panelIntakeCylinders;

    private long originalTimeHatchPanelApparatus = 0l;
    private long originalTimeHatchIntake = 0l;

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
            if(System.currentTimeMillis() - originalTimeHatchPanelApparatus > RobotMap.DelayToOff.hatchPanelApparatus_collapsed.time){

                hatchPanelApparatusState = HatchPanelApparatusState.OFF;

            }

            break;

        case OFF:

            setHatchPanelCylindersOff();

            break;

        case NULL:

            setHatchPanelCylindersOff();

            break;

        }

        switch(hatchIntakeState){

            case RELEASE:
    
                setHatchIntakeCylindersExtended();
    
                break;
    
            case HOLD:
    
                setHatchIntakeCylindersCollapsed();
                if(System.currentTimeMillis() - originalTimeHatchIntake > RobotMap.DelayToOff.hatchIntake_hold.time){
    
                    hatchIntakeState = HatchIntakeState.OFF;
    
                }
    
                break;
    
            case OFF:
    
                setHatchIntakeCylindersOff();
    
                break;
    
            case NULL:
    
                setHatchIntakeCylindersOff();
    
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
        OFF,
        NULL

    }

    public enum HatchIntakeState{

        HOLD,
        RELEASE,
        OFF,
        NULL

    }

    public void setHatchPanelApparatusState(HatchPanelApparatusState state){

        hatchPanelApparatusState = state;
        originalTimeHatchPanelApparatus = System.currentTimeMillis();

    }

    public void setHatchIntakeState(HatchIntakeState state){

        hatchIntakeState = state;
        originalTimeHatchIntake = System.currentTimeMillis();

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

    private void setHatchIntakeCylindersExtended(){

        intakeCylinders.set(Value.kForward);

    }

    private void setHatchIntakeCylindersCollapsed(){

        intakeCylinders.set(Value.kReverse);

    }

    private void setHatchIntakeCylindersOff(){

        intakeCylinders.set(Value.kOff);

    }

}