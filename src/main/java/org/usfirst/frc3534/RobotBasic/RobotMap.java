package org.usfirst.frc3534.RobotBasic;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static WPI_TalonSRX frontRightMotor;
	private static WPI_TalonSRX backRightMotor;
	private static WPI_TalonSRX centerRightMotor;
	public static WPI_TalonSRX frontLeftMotor;
	private static WPI_TalonSRX backLeftMotor;
	private static WPI_TalonSRX centerLeftMotor;

	public static WPI_TalonSRX shooter;

	public static WPI_TalonSRX pidArm;

	public static SpeedControllerGroup rightSideMotors;
	public static SpeedControllerGroup leftSideMotors;

	public static AHRS navx;

	public static Solenoid elevatorCylinderOne;
	public static Solenoid elevatorCylinderTwo;

	public static DigitalInput cylinderSensor;

	public static final double wheelBase_width = 36;
	public static final double robotMaxVeloctiy = 168; // inches per second
	public static final double minMoveSpeed = .375;

	// Wheel Encoder Calculations
	public static final int countsPerRevEncoders = 1440; // 1440 if plugged into talon. 360 if directly into the roborio; just go with, it its weird
	public static final double wheelDiameter = 6.25; // measured in inches
	public static final double inchesPerCountMultiplier = wheelDiameter * Math.PI / countsPerRevEncoders;

	public static void init() {

		frontRightMotor = new WPI_TalonSRX(5);
		frontRightMotor.set(ControlMode.PercentOutput, 0);
		frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		backRightMotor = new WPI_TalonSRX(6);
		backRightMotor.set(ControlMode.PercentOutput, 0);

		centerRightMotor = new WPI_TalonSRX(4);
		centerRightMotor.set(ControlMode.PercentOutput, 0);

		frontLeftMotor = new WPI_TalonSRX(1);
		frontLeftMotor.set(ControlMode.PercentOutput, 0);
		frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		backLeftMotor = new WPI_TalonSRX(2);
		backLeftMotor.set(ControlMode.PercentOutput, 0);

		centerLeftMotor = new WPI_TalonSRX(3);
		centerLeftMotor.set(ControlMode.PercentOutput, 0);

		rightSideMotors = new SpeedControllerGroup(frontRightMotor, backRightMotor, centerRightMotor);
		leftSideMotors = new SpeedControllerGroup(frontLeftMotor, backLeftMotor, centerLeftMotor);
		
		pidArm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		navx = new AHRS(SerialPort.Port.kMXP);

		elevatorCylinderOne = new Solenoid(0, 1);
		elevatorCylinderTwo = new Solenoid(0, 2);

		cylinderSensor = new DigitalInput(0);

	}
}