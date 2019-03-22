package org.usfirst.frc3534.RobotBasic;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
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
	public static WPI_TalonSRX frontLeftMotor;
	private static WPI_TalonSRX backLeftMotor;

	public static SpeedControllerGroup rightSideMotors;
	public static SpeedControllerGroup leftSideMotors;

	public static AHRS navx;

	public static DoubleSolenoid elevatorCylinderOne;
	public static DoubleSolenoid elevatorCylinderTwo;

	public static DigitalInput limitSwitch;

	public static DoubleSolenoid climbingCylinderOne;
	//public static DoubleSolenoid climbingCylinderTwo;

	public static DoubleSolenoid intakeForwardAftCylinders;

	public static DigitalInput rightArmSensor;

	public static WPI_TalonSRX cargoShooterMaster;
	public static WPI_TalonSRX cargoShooterSlave;

	public static WPI_TalonSRX cargoRoller;

	public static SpeedControllerGroup cargoShooter;

	public static DoubleSolenoid panelCylinders;

	public static DoubleSolenoid panelIntakeCylinders;

	public static Compressor compressor;

	public static I2C arduino;

	public static final double wheelBase_width = 24.75;
	public static final double robotMaxVeloctiy = 150; // inches per second
	public static final double minMoveSpeed = .375;

	// Wheel Encoder Calculations
	public static final int countsPerRevEncoders = 1440; // 1440 if plugged into talon. 360 if directly into the roborio; just go with, it its weird
	public static final double wheelDiameter = 6.0; // measured in inches
	public static final double inchesPerCountMultiplier = wheelDiameter * Math.PI / countsPerRevEncoders;
	public static final double codesPer100MillisToInchesPerSecond = inchesPerCountMultiplier * 10;

	public static void init() {

		frontRightMotor = new WPI_TalonSRX(10);
		frontRightMotor.set(ControlMode.PercentOutput, 0);
		frontRightMotor.setNeutralMode(NeutralMode.Brake);
		frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		backRightMotor = new WPI_TalonSRX((9));
		backRightMotor.setNeutralMode(NeutralMode.Brake);
		backRightMotor.set(ControlMode.PercentOutput, 0);

		frontLeftMotor = new WPI_TalonSRX(1);
		frontLeftMotor.setNeutralMode(NeutralMode.Brake);
		frontLeftMotor.set(ControlMode.PercentOutput, 0);
		frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		backLeftMotor = new WPI_TalonSRX(2);
		backLeftMotor.setNeutralMode(NeutralMode.Brake);
		backLeftMotor.set(ControlMode.PercentOutput, 0);

		rightSideMotors = new SpeedControllerGroup(frontRightMotor, backRightMotor);
		leftSideMotors = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);

		navx = new AHRS(SPI.Port.kMXP);

		elevatorCylinderOne = new DoubleSolenoid(1, 0, 1);
		elevatorCylinderTwo = new DoubleSolenoid(1, 2, 3);

		limitSwitch = new DigitalInput(0);

		climbingCylinderOne = new DoubleSolenoid(2, 4, 5);
		//climbingCylinderTwo = new DoubleSolenoid(2, 6, 7);

		intakeForwardAftCylinders = new DoubleSolenoid(2, 2, 3);

		rightArmSensor = new DigitalInput(1);

		cargoShooterMaster = new WPI_TalonSRX(6);
		cargoShooterSlave = new WPI_TalonSRX(7);
		cargoShooterSlave.setInverted(true);

		cargoShooter = new SpeedControllerGroup(cargoShooterMaster, cargoShooterSlave);

		cargoRoller = new WPI_TalonSRX(4);

		panelCylinders = new DoubleSolenoid(1, 4, 5);

		panelIntakeCylinders = new DoubleSolenoid(2, 6, 7);

		compressor = new Compressor(1);
        compressor.setClosedLoopControl(true);

		arduino = new I2C(I2C.Port.kOnboard, 111);

	}

	public enum DelayToOff{

		climber_retract(4.0),
		elevator_stage1a(3.0),
		elevator_stage1b(3.0),
		elevator_stage2(3.0),
		elevator_floor(3.0),
		hatchPanelApparatus_collapsed(2.0),
		hatchIntake_hold(2.0),
		armExtend_extended(3.0),
		armLift_collapsed(2.0),
		armLift_mid(2.0),
		armLift_up(2.0);

		public double time;

		private DelayToOff(double time){

			this.time = time * 1000;

		}
	}

	public enum FunctionStateDelay{

		cargoIntakeFloor_elevatorStage1A_to_armExtendExtended_rollerIntake(1.0),
		cargoShoot_shooterShoot_to_shooterStop(1.0),
		habLevel3ClimbPart1_elevatorStage1A_to_armExtendExtended(1.0),
		habLevel3ClimbPart1_armExtendExtended_to_armLiftUp(1.0),
		habLevel3ClimbPart2_armLiftCollapsed_to_climberClimb(3.0),
		habLevel3ClimbPart2_armLiftMid_climberRetract_to_armExtendCollapsed(3.0),
		hatchPlace_hatchIntakeRelease_to_hatchPanelApparatusExtended(0.25),
		hatchPlace_hatchPanelApparatusExtended_to_hatchPanelApparatusCollapsed(0.25),
		xButtonReset_armLiftMid_to_armExtendCollapsed(1.0);

		public double time;

		private FunctionStateDelay(double time){

			this.time = time * 1000;

		}
	}

	public enum PowerOutput{

		intake_roller_intake(0.8),
		intake_roller_stop(0.0),
		shooter_shooter_intake(-0.5),
		shooter_shooter_shoot(1.0),
		shooter_shooter_stop(0.0);

		public double power;

		private PowerOutput(double power){

			this.power = power;

		}

	}
}