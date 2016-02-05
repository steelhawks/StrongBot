package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.Drive;
import org.usfirst.frc.team2601.robot.util.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	Constants constants = Constants.getInstance();
	
	HawkCANTalon frontLeftMotor = new HawkCANTalon(constants.frontLeftTalon, "frontLeftMotor");
	HawkCANTalon backLeftMotor = new HawkCANTalon(constants.backLeftTalon, "backLeftMotor");
	HawkCANTalon frontRightMotor = new HawkCANTalon(constants.frontRightTalon, "frontRighMotor");
	HawkCANTalon backRightMotor = new HawkCANTalon(constants.backRightTalon, "backRightMotor");
	
	HawkDoubleSolenoid leftShift = new HawkDoubleSolenoid(constants.leftSolenoidOn,constants.leftSolenoidOff, "leftSolenoid");
	HawkDoubleSolenoid rightShift = new HawkDoubleSolenoid(constants.rightSolenoidOn,constants.rightSolenoidOff, "rightSolenoid");
	

	RobotDrive drive = new RobotDrive(frontLeftMotor, frontRightMotor);
	
	PIDController leftSide;
	PIDController rightSide;
	
	Encoder leftEncoder;
	Encoder rightEncoder;
    
	//this is used for the logger
	private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
	public HawkLogger logger;
		
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Drivetrain(){
	/*	leftSide = new PIDController(constants.kP, constants.kI, constants.kD, constants.kF, leftEncoder, frontLeftMotor);
		rightSide = new PIDController(constants.kP,constants.kI, constants.kD, constants.kF, rightEncoder,frontRightMotor);
		leftSide.setPercentTolerance(constants.PIDtolerance);
		rightSide.setPercentTolerance(constants.PIDtolerance);
		
		//ready encoders
	    leftEncoder.setDistancePerPulse(constants.distancePerPulse);
		rightEncoder.setDistancePerPulse(constants.distancePerPulse);
		
		//ready shifting gearboxes
		leftShift.set(DoubleSolenoid.Value.kForward);
		matchSolenoids();
		*/

		loggingList.add(frontLeftMotor);
		loggingList.add(backLeftMotor);
		loggingList.add(frontRightMotor);
		loggingList.add(backRightMotor);
		loggingList.add(leftShift);
		loggingList.add(rightShift);
		//loggingList.add(leftEncoder);
		//loggingList.add(rightEncoder);

		//ready logger
		logger = new HawkLogger("drivetrain", loggingList);
		logger.setup();

		//ready shifting gearboxes
		leftShift.set(HawkDoubleSolenoid.Value.kForward);
		matchSolenoids();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    private void matchMotors(HawkCANTalon leader, HawkCANTalon follower){
    	follower.set(leader.get());
    	logger.log(constants.logging);
    }
    public void arcadeDrive(double move, double rotate){
    	drive.arcadeDrive(move, rotate);
    	matchMotors(frontLeftMotor, backLeftMotor);
    	matchMotors(frontRightMotor, backRightMotor);
    	logger.log(constants.logging);
    }
    public void arcadeDriveTwist(Joystick stick){
    	double move = stick.getY();
    	double rotate = stick.getTwist();
    	arcadeDrive(move,rotate);
    }
    public void shiftGears(){
    	if (rightShift.get() == HawkDoubleSolenoid.Value.kForward){
    		rightShift.set(HawkDoubleSolenoid.Value.kReverse);
    	}else {
    		rightShift.set(HawkDoubleSolenoid.Value.kForward);
    	}
    	matchSolenoids();
    }
    private void matchSolenoids(){
    	leftShift.set(rightShift.get());
    	logger.log(constants.logging);
    }
  /*//this variable locks in the BangBang method, allows it to set final distance once per successful run.
    private boolean bangBangStarted = false;
    private double finalLeftLocation = 0;
    private double finalRightLocation = 0;
    
    public boolean bangBang(double distance, double threshold, double speed){
    	double currentLeftDistance = leftEncoder.getDistance();
    	double currentRightDistance = rightEncoder.getDistance();
    	double left = 0, right = 0;
    	boolean leftTruth, rightTruth;
    	
    	if(!bangBangStarted){
    		finalRightLocation = distance + currentRightDistance;
    		finalLeftLocation = distance + currentLeftDistance;
    	}
    	
    	if (currentLeftDistance > finalLeftLocation - threshold && currentLeftDistance < finalLeftLocation + threshold){
    		leftTruth = true;
    	}
    	else{
    		left = speed;
    		leftTruth = false;
    	}	
    	if (currentRightDistance > finalRightLocation - threshold && currentRightDistance < finalRightLocation + threshold){
    		rightTruth = true;
    	}
    	else{
    		right = speed;
    		rightTruth = false;
    	}
    	
    	if (leftTruth && rightTruth){
    		bangBangStarted = false;
    		finalLeftLocation = 0;
    		finalRightLocation = 0;
    		return true;
    	}
    	//tankDrive(left, right);
    	arcadeDriveTwist(constants.stick);
    	bangBangStarted = true;
    	return false;
  
    }
    //double variable bangbang
    public boolean bangBang(double distance, double threshold){
    	return bangBang(distance, threshold, constants.defaultBangBangSpeed);
    }
    
    //End bangBang autonomous
    
    private boolean PIDinitialized = false;
    
    public void setBothPID(double setpoint){
    	leftSide.setSetpoint(leftEncoder.getDistance()+setpoint);
    	rightSide.setSetpoint(rightEncoder.getDistance()+setpoint);
    	PIDinitialized = true;
    }
    
    public void setIndividualPID(double leftSet, double rightSet){
    	leftSide.setSetpoint(leftEncoder.getDistance()+leftSet);
    	rightSide.setSetpoint(rightEncoder.getDistance()+rightSet);
    	PIDinitialized = true;
    }
    public boolean runPID(double left, double right){
    	//make sure we have setpoints
    	if (PIDinitialized){
	    	//start moving
    		leftSide.enable();
	    	rightSide.enable();
	    	matchMotors(frontRightMotor, backRightMotor);
	    	matchMotors(frontLeftMotor, backLeftMotor);
	    	//check if we're on target
	    	if (leftSide.onTarget() && rightSide.onTarget()){
	    		leftSide.disable();
	    		rightSide.disable();
	    		PIDinitialized = false;
	    		return true;
	    	}
	    	logger.log(constants.logging);
	    	return false;
    	}
    	//set setpoints if we don't already have them
    	else {
    		setIndividualPID(left, right);
    		return false;
    	}
    }
    public boolean runPID(double setpoint){
    	//make sure we have setpoints
    	if (PIDinitialized){
    		//start moving
    		leftSide.enable();
	    	rightSide.enable();
	    	matchMotors(frontRightMotor, backRightMotor);
	    	matchMotors(frontLeftMotor, backLeftMotor);
	    	//check if we're on target
	    	if (leftSide.onTarget() && rightSide.onTarget()){
	    		leftSide.disable();
	    		rightSide.disable();
	    		PIDinitialized = false;
	    		return true;
	    	}
	    	logger.log(constants.logging);
	    	return false;
    	}
    	//set setpoints if we don't already have them
    	else {
    		setBothPID(setpoint);
    		return false;
    	}
    }*/
}