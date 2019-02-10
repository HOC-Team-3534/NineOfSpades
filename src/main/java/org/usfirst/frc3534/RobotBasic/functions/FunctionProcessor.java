package org.usfirst.frc3534.RobotBasic.functions;

public class FunctionProcessor{

    static ArmsUp armsUp;
    static CargoFloorIntake cargoFloorIntake;
    static Climb climb;
    static PlacePanel placePanel;
    static Retract retract;

    public FunctionProcessor(){

        armsUp = new ArmsUp();
        cargoFloorIntake = new CargoFloorIntake();
        climb = new Climb();
        placePanel = new PlacePanel();
        retract = new Retract();

    }

    public void process(){





    }

}