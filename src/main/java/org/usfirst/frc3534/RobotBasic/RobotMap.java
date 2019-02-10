package org.usfirst.frc3534.RobotBasic;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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

	public static SpeedControllerGroup rightSideMotors;
	public static SpeedControllerGroup leftSideMotors;

	public static AHRS navx;

	public static DoubleSolenoid elevatorCylinderOne;
	public static Solenoid elevatorCylinderTwo;

	public static DigitalInput limitSwitch;

	public static Solenoid climbingCylinderOne;
	public static Solenoid climbingCylinderTwo;

	public static Solenoid leftShortArmCylinder;
	public static Solenoid leftLongArmCylinder;
	public static Solenoid rightShortArmCylinder;
	public static Solenoid rightLongArmCylinder;

	public static Solenoid leftArmExtendCylinder;
	public static Solenoid rightArmExtendCylinder;

	public static WPI_TalonSRX cargoShooterMaster;
	public static WPI_TalonSRX cargoShooterSlave;

	public static WPI_TalonSRX cargoRoller;

	public static SpeedControllerGroup cargoShooter;

	public static Solenoid panelCylinder1;
	public static Solenoid panelCylinder2;
	public static Solenoid panelCylinder3;

	public static final double wheelBase_width = 36;
	public static final double robotMaxVeloctiy = 168; // inches per second
	public static final double minMoveSpeed = .375;

	// Wheel Encoder Calculations
	public static final int countsPerRevEncoders = 1440; // 1440 if plugged into talon. 360 if directly into the roborio; just go with, it its weird
	public static final double wheelDiameter = 6.25; // measured in inches
	public static final double inchesPerCountMultiplier = wheelDiameter * Math.PI / countsPerRevEncoders;

	public static void init() {

		frontRightMotor = new WPI_TalonSRX(10);
		frontRightMotor.set(ControlMode.PercentOutput, 0);
		frontRightMotor.setNeutralMode(NeutralMode.Brake);
		frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		backRightMotor = new WPI_TalonSRX((9));
		backRightMotor.setNeutralMode(NeutralMode.Brake);
		backRightMotor.set(ControlMode.PercentOutput, 0);

		centerRightMotor = new WPI_TalonSRX(8);
		centerRightMotor.setNeutralMode(NeutralMode.Brake);
		centerRightMotor.set(ControlMode.PercentOutput, 0);

		frontLeftMotor = new WPI_TalonSRX(1);
		frontLeftMotor.setNeutralMode(NeutralMode.Brake);
		frontLeftMotor.set(ControlMode.PercentOutput, 0);
		frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		backLeftMotor = new WPI_TalonSRX(2);
		backLeftMotor.setNeutralMode(NeutralMode.Brake);
		backLeftMotor.set(ControlMode.PercentOutput, 0);

		centerLeftMotor = new WPI_TalonSRX(3);
		centerLeftMotor.setNeutralMode(NeutralMode.Brake);
		centerLeftMotor.set(ControlMode.PercentOutput, 0);

		rightSideMotors = new SpeedControllerGroup(frontRightMotor, backRightMotor, centerRightMotor);
		leftSideMotors = new SpeedControllerGroup(frontLeftMotor, backLeftMotor, centerLeftMotor);

		navx = new AHRS(SerialPort.Port.kMXP);

		/*
		elevatorCylinderOne = new DoubleSolenoid(0, 0, 1);
		elevatorCylinderTwo = new Solenoid(0, 2);

		limitSwitch = new DigitalInput(0);

		climbingCylinderOne = new Solenoid(0, 3);
		climbingCylinderTwo = new Solenoid(0, 4);

		leftShortArmCylinder = new Solenoid(1, 0);
		leftLongArmCylinder = new Solenoid(1, 1);
		rightShortArmCylinder = new Solenoid(1, 2);
		rightLongArmCylinder = new Solenoid(1, 3);

		leftArmExtendCylinder = new Solenoid(1, 4);
		rightArmExtendCylinder = new Solenoid(1, 5);

		cargoShooterMaster = new WPI_TalonSRX(6);
		cargoShooterSlave = new WPI_TalonSRX(7);

		cargoShooter = new SpeedControllerGroup(cargoShooterMaster, cargoShooterSlave);

		cargoRoller = new WPI_TalonSRX(4);

		panelCylinder1 = new Solenoid(0, 5);
		panelCylinder2 = new Solenoid(0, 6);
		panelCylinder3 = new Solenoid(0, 7);
		*/


	}
}