package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;

public class FunctionProcessor{

    static ArmsUp armsUp;
    static CargoFloorIntakeIn cargoFloorIntakeIn;
    static CargoFloorIntakeOut cargoFloorIntakeOut;
    static CargoTopIntake cargoTopIntake;
    static Climb climb;
    static Elevate elevate;
    static PlacePanel placePanel;
    static RetractClimber retractClimber;
    static ShootCargo shootCargo;

    public FunctionProcessor(){

        armsUp = new ArmsUp();
        cargoFloorIntakeIn = new CargoFloorIntakeIn();
        cargoFloorIntakeOut = new CargoFloorIntakeOut();
        cargoTopIntake = new CargoTopIntake();
        climb = new Climb();
        elevate = new Elevate();
        placePanel = new PlacePanel();
        retractClimber = new RetractClimber();
        shootCargo = new ShootCargo();

    }

    public void process(){

        if(Robot.oi.getController2().getYButton() || armsUp.running){

            armsUp.process();

        }else if(armsUp.isFinished()){

            armsUp.reset();

        }

        if((armsUp.isFinished() && Robot.oi.getController1().getYButton()) || climb.running){

            climb.process();
            retractClimber.reset();

        }else if(climb.isFinished() || (armsUp.running && !Robot.oi.getController2().getYButton())){

            if(!retractClimber.isFinished()){

                retractClimber.process();

            }else{

                climb.reset();
                armsUp.reset();

            }

        }

        if(Robot.oi.getController2().getAButton() || cargoFloorIntakeIn.running){

            cargoFloorIntakeIn.process();
            cargoFloorIntakeOut.reset();

        }else if(cargoFloorIntakeIn.isFinished()){

            if(!cargoFloorIntakeOut.isFinished()){
            
                cargoFloorIntakeOut.process();

            }else{

                cargoFloorIntakeIn.reset();

            }

        }

        if(Robot.oi.getController2().getBButton() || cargoTopIntake.running){

            cargoTopIntake.process();

        }else if(cargoTopIntake.isFinished()){

            cargoTopIntake.reset();

        }

        if(Robot.oi.getController1().getBButton() || shootCargo.running){

            shootCargo.process();

        }else if(shootCargo.isFinished()){

            shootCargo.reset();

        }

        if(Robot.oi.getController1().getAButton() || placePanel.running){

            placePanel.process();

        }else if(placePanel.isFinished()){

            placePanel.reset();

        }

        if(((!climb.running && !retractClimber.running) && (!cargoFloorIntakeIn.running && !cargoFloorIntakeOut.running)) && !cargoTopIntake.running){

            elevate.process();

        }





    }

}