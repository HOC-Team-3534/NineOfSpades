package org.usfirst.frc3534.RobotBasic;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3534.RobotBasic.functions.FunctionProcessor;
import org.usfirst.frc3534.RobotBasic.systems.*;
import org.usfirst.frc3534.RobotBasic.systems.Elevator.ElevatorState;
import org.usfirst.frc3534.RobotBasic.systems.HatchPanelApparatus.HatchPanelApparatusState;
import org.usfirst.frc3534.RobotBasic.systems.Intake.ArmExtendState;

import Autons.AutonStateMachine0;
import Autons.AutonStateMachine1;
import Autons.AutonStateMachine2;
import Autons.AutonStateMachine3;
import Autons.AutonStateMachineInterface;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static int AUTON_PERIODIC = 1;
	public static OI oi;
	public static Drive drive;
	public static Elevator elevator;
	public static Intake intake;
	public static HatchPanelApparatus hatchPanelApparatus;
	public static Arduino arduino;
	public static Shooter shooter;
	public static FunctionProcessor functionProcessor;

	private int loopPeriod = 0;
	private int loopCnt = 0;
	private int logCounter = 0;

	public static double designatedLoopPeriod = 20; // in milliseconds. milliseconds = seconds/1000. seconds to
													// milliseconds . seconds * 1000 = milliseconds

	public static boolean autonomous;
	public static boolean teleop;
	public static boolean enabled;

	public static boolean firstTimeAutonomous = true;

	public static boolean firstTimeEnabled = true;

	private AutonStateMachineInterface autonStateMachine;
	SendableChooser compressorChooser = new SendableChooser();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {

		RobotMap.init();

		drive = new Drive();
		elevator = new Elevator();
		intake = new Intake();
		hatchPanelApparatus = new HatchPanelApparatus();
		arduino = new Arduino();
		shooter = new Shooter();

		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		functionProcessor = new FunctionProcessor();

		compressorChooser.addDefault("ON", new Boolean(true));
		compressorChooser.addObject("OFF", new Boolean(false));

		//SmartDashboard.putData("Compressor ON / OFF", compressorChooser);

	}

	/**
	 * This function is called when the disabled button is hit. You can use it to
	 * reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {

		//SmartDashboard.putNumber("autonMode", 0);

	}

	@Override
	public void disabledPeriodic() {

		elevator.setElevatorState(ElevatorState.NULL);
		hatchPanelApparatus.setHatchPanelApparatusState(HatchPanelApparatusState.NULL);
		intake.setArmExtendState(ArmExtendState.NULL);
		firstTimeEnabled = true;
		firstTimeAutonomous = true;

	}

	@Override
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

		teleopPeriodic();

	}

	@Override
	public void teleopInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {

		if(firstTimeEnabled){

			firstTimeEnabled = false;

		}

		log();

		long prevLoopTime = 0;

		while (this.isEnabled()) {

			log();

			RobotState("teleop enabled");

			long currentTime = System.currentTimeMillis();

			if (currentTime - prevLoopTime >= designatedLoopPeriod) {

				loopPeriod = (int) (currentTime - prevLoopTime);
				prevLoopTime = currentTime;
				loopCnt++;

				// run processes
				drive.process();
				functionProcessor.process();
				shooter.process();
				elevator.process();
				intake.process();
				hatchPanelApparatus.process();

				if((Boolean)compressorChooser.getSelected()){

					RobotMap.compressor.start();

				}else{

					RobotMap.compressor.stop();

				}

			}

			Timer.delay(0.001);

		}

		RobotState("teleop disabled");

	}

	public void log() {

		logCounter++;

		if (logCounter >= 5) {

			// SmartDashboard Numbers
			/*
			SmartDashboard.putNumber("Loop Period", loopPeriod);
			SmartDashboard.putNumber("Loop Count", loopCnt);
			SmartDashboard.putBoolean("Compressor", RobotMap.compressor.enabled());
			SmartDashboard.putBoolean("extension", intake.isArmAft());
			*/
			logCounter = 0;

		}
	}

	public void RobotState(String state) {

		switch (state) {

		case "teleop enabled":

			autonomous = false;
			teleop = true;
			enabled = true;
			break;

		case "teleop disabled":

			autonomous = false;
			teleop = true;
			enabled = false;
			break;

		case "autonomous enabled":

			autonomous = true;
			teleop = false;
			enabled = true;
			break;

		case "autonomous disabled":

			autonomous = true;
			teleop = false;
			enabled = false;
			break;

		}

	}
}