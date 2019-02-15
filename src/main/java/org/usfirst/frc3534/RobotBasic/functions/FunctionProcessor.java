package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

public class FunctionProcessor{

    static HabLevel3ClimbPart1 habLevel3ClimbPart1;
    static CargoIntakeFloor cargoIntakeFloor;
    static CargoIntakeTop cargoIntakeTop;
    static HabLevel3ClimbPart2 habLevel3ClimbPart2;
    static Elevate elevate;
    static HatchPlace hatchPlace;
    static CargoShoot cargoShoot;

    public FunctionProcessor(){

        habLevel3ClimbPart1 = new HabLevel3ClimbPart1();
        cargoIntakeFloor = new CargoIntakeFloor();
        cargoIntakeTop = new CargoIntakeTop();
        habLevel3ClimbPart2 = new HabLevel3ClimbPart2();
        elevate = new Elevate();
        hatchPlace = new HatchPlace();
        cargoShoot = new CargoShoot();

    }

    public void process(){

        

    }

}