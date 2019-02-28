package org.usfirst.frc3534.RobotBasic;

import java.util.concurrent.Callable;

import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	final XboxPlusPOV xbox1 = new XboxPlusPOV(0);
	final XboxPlusPOV xbox2 = new XboxPlusPOV(1);

	public OI() {

		// SmartDashboard Buttons

	}

	public XboxPlusPOV getController1() {

		return xbox1;

	}

	public XboxPlusPOV getController2() {

		return xbox2;

	}

	public static enum Buttons {

		CargoIntakeFloor(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController1().getAButton());

            }

        }),
        CargoIntakeTop(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController1().getYButton());

            }

        }),
        CargoShoot(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController2().getBumper(Hand.kRight));

            }

        }),
        HatchPickUp(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController1().getBButton());

            }

        }),
        Elevate_Stage2(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController2().getYButton());

            }

        }),
        Elevate_Stage1B(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController2().getBButton());

            }

        }),
        Elevate_Stage1A(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController2().getAButton());

            }

        }),
        Elevate_Floor(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController1().getAButton());

            }

        }),
        HabLevel3ClimbPart1(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController2().getXButton());

            }

        }),
        HabLevel3ClimbPart2(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController1().getXButton());

            }

        }),
        XButtonReset(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{

                return Boolean.valueOf(Robot.oi.getController2().getBackButton());

            }

        });

        Callable<Boolean> callable;

		private Buttons(Callable<Boolean> callable){

            this.callable = callable;

        }
        
        public boolean getButton(){

            try{

                return callable.call().booleanValue();

            }catch(Exception ex){

                return false;

            }

        }

    }
    
    public static enum Axes {

        HatchPlace(new Callable<Double>(){

            @Override
            public Double call() throws Exception{

                return Robot.oi.getController2().getTriggerAxis(Hand.kRight);

            }

        }),
        DriverTargetMode(new Callable<Double>(){

            @Override
            public Double call() throws Exception{

                return Robot.oi.getController1().getTriggerAxis(Hand.kLeft);

            }

		}),
		Drive_ForwardBackward(new Callable<Double>(){

            @Override
            public Double call() throws Exception{

                return -Robot.oi.getController1().getY(Hand.kLeft);

            }

		}),
		Drive_LeftRight(new Callable<Double>(){

            @Override
            public Double call() throws Exception{

                return Robot.oi.getController1().getX(Hand.kLeft);

            }

		});

        Callable<Double> callable;

		private Axes(Callable<Double> callable){

            this.callable = callable;

        }
        
        public double getAxis(){

            try{

                return callable.call().doubleValue();

            }catch(Exception ex){

                return 0.0;

            }

        }

	}
}