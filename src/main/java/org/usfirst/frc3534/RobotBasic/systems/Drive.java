package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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

	public Drive() {

		drive = new DifferentialDrive(leftSide, rightSide);
		drive.setSafetyEnabled(true);
		drive.setMaxOutput(1.0);

	}

	@Override
	public void process() {

		if (Robot.teleop && Robot.enabled) {

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
				
				drive.arcadeDrive(yOut * 0.6, xOut);

			}else{

				drive.arcadeDrive(yOut, xOut);

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