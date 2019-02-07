import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;

public class Cylinder extends SystemBase implements SystemInterface {

    private DigitalInput cylinderSensor = RobotMap.cylinderSensor;
    private boolean firstTime = true;

    private final int EXTEND = 1;
    private final int STOP = 0;
    private final int states[] = {EXTEND, STOP};

    private boolean extend = false;

    private boolean finishes[] = {false, false}; 

    public Cylinder() {
    }

    @Override
    public void process() {



    }

}