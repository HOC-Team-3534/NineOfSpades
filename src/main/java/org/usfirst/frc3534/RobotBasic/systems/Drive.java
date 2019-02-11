package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends SystemBase implements SystemInterface {

	private SpeedControllerGroup rightSide = RobotMap.rightSideMotors;
	private SpeedControllerGroup leftSide = RobotMap.leftSideMotors;
	private DifferentialDrive drive;

	private double rightPower, leftPower;

	private double deadband = 0.10;
	private double turningDeadband = 0.20;
	private boolean negative = false;

	private double yInput, xInput;
	private double yOut, xOut;

	private double left_command = 0.0, right_command = 0.0;

	private double last_error, distance_last_error;

	private double KpAim = 0.065;
	private double KdAim = 0.004;
	private double KpDistance = 0.03;
	private double KdDistance = .015;
	private double kpTranslation = 0.007;
	private double min_aim_command = 0.005;
	private double max_side_to_side_correction = 0.1;

	private double[] defaultArray = {0.0, 0.0, 0.0, 0.0, 0.0 , 0.0};

	public Drive() {

		drive = new DifferentialDrive(leftSide, rightSide);
		drive.setSafetyEnabled(true);
		drive.setMaxOutput(1.0);

	}

	@Override
	public void process() {

		if (Robot.teleop && Robot.enabled) {

			//Network 
			//Attempt at calling the Network Tables for Limelight and setting it 
			NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
			double tx = table.getEntry("tx").getDouble(0.0);
			SmartDashboard.putNumber("tx", tx);
			double ty = table.getEntry("ty").getDouble(0.0);
			double[] _3d6Axis = table.getEntry("camtran").getDoubleArray(defaultArray);

			if(Robot.oi.getController1().getAButton()){

				double heading_error = tx;
				double distance_error = ty;
				double steering_adjust = 0.0;

				if ( tx > 1.0 ) {

					steering_adjust = KpAim * heading_error + min_aim_command  + (heading_error - last_error) * KdAim ;
		
				}
				else if ( tx < -1.0 ) {

					steering_adjust = KpAim * heading_error - min_aim_command + (heading_error - last_error) * KdAim;
				
				}else{

					steering_adjust = 0.0;
					left_command = 0.0;
					right_command = 0.0;

				}

				last_error = heading_error;

				double distance_adjust = KpDistance * distance_error + KdDistance * (distance_error - distance_last_error);
				
				distance_last_error = distance_error;

				left_command = steering_adjust + distance_adjust ;
				right_command = -steering_adjust + distance_adjust ;

				double sideToSideCorrection = Math.abs(_3d6Axis[0]) * kpTranslation;
				if(sideToSideCorrection > max_side_to_side_correction){

					sideToSideCorrection = max_side_to_side_correction;

				}

				if(_3d6Axis[0] > 0){

					right_command += sideToSideCorrection;

				}else{

					left_command += sideToSideCorrection;

				}

				drive.tankDrive(left_command, right_command);

			}else{

				negative = false;
				yInput = -Robot.oi.getController1().getY(Hand.kLeft);
				if(yInput < 0) negative = true;

				yOut = Math.abs(yInput);

				if(yOut > deadband){

					yOut -= deadband;
					yOut *= (1 / (1 - deadband));
					yOut = Math.pow(yOut, 2) * (1 - RobotMap.minMoveSpeed) + RobotMap.minMoveSpeed;
					if(negative) yOut = -yOut;

				}else{

					yOut = 0;

				}

				negative = false;
				xInput = Robot.oi.getController1().getX(Hand.kLeft);
				if(xInput < 0) negative = true;
				
				xOut = Math.abs(xInput);
				if(xOut > turningDeadband){

					xOut -= turningDeadband;
					xOut *= Math.pow((1 / (1 - turningDeadband)), 2);
					if(negative) xOut = -xOut;

				}else{

					xOut = 0;

				}

				if(Robot.oi.getController1().getTriggerAxis(Hand.kRight) >= 0.5){
					
					drive.arcadeDrive(yOut * 0.6, xOut * 0.5);;

				}else{

					drive.arcadeDrive(yOut, xOut * 0.7);

				}

			}


		} else if (Robot.autonomous) {

			drive.tankDrive(leftPower, rightPower);

		}

		// uncomment the following code to test for max velocity
		/*
		 * double velocity;
		 * 
		 * if(RobotMap.frontLeftMotor.getSensorCollection().getQuadratureVelocity() >
		 * RobotMap.frontRightMotor.getSensorCollection().getQuadratureVelocity()) {
		 * 
		 * velocity =
		 * RobotMap.frontLeftMotor.getSensorCollection().getQuadratureVelocity();
		 * 
		 * }else{
		 * 
		 * velocity =
		 * RobotMap.frontRightMotor.getSensorCollection().getQuadratureVelocity();
		 * 
		 * }
		 * 
		 * SmartDashboard.putNumber("Velocity", velocity);
		 */

	}

	public void setRightPower(double power) {

		rightPower = power;

	}

	public void setLeftPower(double power) {

		leftPower = power;

	}

	public double getNavXAngle(){

		return RobotMap.navx.getRoll();

	}
}