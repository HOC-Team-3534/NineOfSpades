package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.systems.Arduino.LEDState;

public class FunctionProcessor{

    HabLevel3ClimbPart1 habLevel3ClimbPart1;
    CargoIntakeFloor cargoIntakeFloor;
    CargoIntakeTop cargoIntakeTop;
    HabLevel3ClimbPart2 habLevel3ClimbPart2;
    Elevate elevate;
    HatchPlace hatchPlace;
    CargoShoot cargoShoot;
    XButtonReset xButtonReset;

    public FunctionProcessor(){

        habLevel3ClimbPart1 = new HabLevel3ClimbPart1();
        cargoIntakeFloor = new CargoIntakeFloor();
        cargoIntakeTop = new CargoIntakeTop();
        habLevel3ClimbPart2 = new HabLevel3ClimbPart2();
        elevate = new Elevate();
        hatchPlace = new HatchPlace();
        cargoShoot = new CargoShoot();
        xButtonReset = new XButtonReset();

    }

    public void process(){

        Robot.arduino.setLEDState(LEDState.BLUE);
        cargoIntakeFloor.process();
        cargoIntakeTop.process();
        habLevel3ClimbPart1.process();
        habLevel3ClimbPart2.process();
        xButtonReset.process();
        hatchPlace.process();
        cargoShoot.process();
        elevate.process();

    }

}