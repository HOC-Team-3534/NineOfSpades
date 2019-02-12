package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

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

        if(Robot.oi.getController2().getXButton() || armsUp.running){

            armsUp.process();

        }else if(armsUp.isFinished()){

            armsUp.reset();

        }

        if((armsUp.isFinished() && Robot.oi.getController1().getXButton()) || climb.running){

            climb.process();
            retractClimber.reset();

        }else if(climb.isFinished() || (armsUp.running && !Robot.oi.getController2().getXButton())){

            if(!retractClimber.isFinished()){

                retractClimber.process();

            }else{

                climb.reset();
                armsUp.reset();

            }

        }

        if(Robot.oi.getController1().getAButton() || cargoFloorIntakeIn.running){

            cargoFloorIntakeIn.process();
            cargoFloorIntakeOut.reset();

        }else if(cargoFloorIntakeIn.isFinished()){

            if(!cargoFloorIntakeOut.isFinished()){
            
                cargoFloorIntakeOut.process();

            }else{

                cargoFloorIntakeIn.reset();

            }

        }

        if(Robot.oi.getController1().getBButton() || cargoTopIntake.running){

            cargoTopIntake.process();

        }else if(cargoTopIntake.isFinished()){

            cargoTopIntake.reset();

        }

        if((Robot.oi.getController2().getBumper(Hand.kRight) || shootCargo.running) && !cargoTopIntake.running){

            shootCargo.process();

        }else if(shootCargo.isFinished()){

            shootCargo.reset();

        }

        if(Robot.oi.getController2().getTriggerAxis(Hand.kRight) >= 0.5 || placePanel.running){

            placePanel.process();

        }else if(placePanel.isFinished()){

            placePanel.reset();

        }

        if(((!climb.running && !retractClimber.running) && (!cargoFloorIntakeIn.running && !cargoFloorIntakeOut.running)) && (!cargoTopIntake.running && !armsUp.running)){

            elevate.process();

        }





    }

}