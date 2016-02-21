package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.Drive;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ArcadeDriveSimple;
import org.usfirst.frc.team2601.robot.util.*;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	Constants constants = Constants.getInstance();
	
	HawkCANTalon frontLeftMotor = new HawkCANTalon(constants.frontLeftTalon, "frontLeftMotor");
	HawkCANTalon backLeftMotor = new HawkCANTalon(constants.backLeftTalon, "backLeftMotor");
	HawkCANTalon frontRightMotor = new HawkCANTalon(constants.frontRightTalon, "frontRighMotor");
	HawkCANTalon backRightMotor = new HawkCANTalon(constants.backRightTalon, "backRightMotor");
	
	/*CANTalon frontLeftMotor = new CANTalon(constants.frontLeftTalon);
	CANTalon backLeftMotor = new CANTalon(constants.backLeftTalon);
	CANTalon frontRightMotor = new CANTalon(constants.frontRightTalon);
	CANTalon backRightMotor = new CANTalon(constants.backRightTalon);
	*/
	HawkDoubleSolenoid leftShift = new HawkDoubleSolenoid(constants.leftSolenoidOn,constants.leftSolenoidOff, "leftSolenoid");
	HawkDoubleSolenoid rightShift = new HawkDoubleSolenoid(constants.rightSolenoidOn,constants.rightSolenoidOff, "rightSolenoid");
	
	
	
	Ultrasonic ultrasonic = new Ultrasonic(constants.ultrasonicInput, constants.ultrasonicOutput);
	double ultrasonicValue;
	Gyro gyro = new AnalogGyro(constants.gyroAnalogPin);
	double gyroAngle;
	double gyroRate;
	
	RobotDrive drive = new RobotDrive(frontLeftMotor, frontRightMotor);
	
	PIDController leftSide;
	PIDController rightSide;
	
	Encoder leftEncoder;
	Encoder rightEncoder;
    
	private boolean gear = true;
	//this is used for the logger
	private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
	public HawkLogger logger;
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Drivetrain(){
		try{
			leftEncoder = new Encoder(constants.leftEncoderPortI, constants.leftEncoderPortII, false, EncodingType.k4X);
			rightEncoder = new Encoder(constants.rightEncoderPortI, constants.rightEncoderPortII, true, EncodingType.k4X);
		//try catch
			leftSide = new PIDController(constants.kP, constants.kI, constants.kD, constants.kF, leftEncoder, frontLeftMotor);
			rightSide = new PIDController(constants.kP,constants.kI, constants.kD, constants.kF, rightEncoder,frontRightMotor);
		} catch(Exception e){
			System.out.println("Error initializing encoders; Check if encoders and PIDs are plugged in to RIO"); 
		}
		leftSide.setPercentTolerance(constants.PIDtolerance);
		rightSide.setPercentTolerance(constants.PIDtolerance);
		
		//ready encoders
	    leftEncoder.setDistancePerPulse(constants.distancePerPulse);
		rightEncoder.setDistancePerPulse(constants.distancePerPulse);

		

	//	loggingList.add(frontLeftMotor);
	//	loggingList.add(backLeftMotor);
	//	loggingList.add(frontRightMotor);
	//	loggingList.add(backRightMotor);
		loggingList.add(leftShift);
		loggingList.add(rightShift);
		//loggingList.add(leftEncoder);
		//loggingList.add(rightEncoder);

		//ready logger
		logger = new HawkLogger("drivetrain", loggingList);
		logger.setup();

		//ready shifting gearboxes
		rightShift.set(HawkDoubleSolenoid.Value.kForward);
		matchSolenoids();
		
		//ready Ultrasonics
		ultrasonic.setEnabled(true);
		ultrasonic.setAutomaticMode(true);
		
		gyro.reset();
		
		frontLeftMotor.setSafetyEnabled(false);
		frontRightMotor.setSafetyEnabled(false);
		backLeftMotor.setSafetyEnabled(false);
		backRightMotor.setSafetyEnabled(false);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    private void matchMotors(HawkCANTalon leader, HawkCANTalon follower){
    	follower.set(leader.get());
    	//logger.log(constants.logging);
    }
    public void arcadeDrive(double move, double rotate){
    	drive.arcadeDrive(move, rotate);
    	matchMotors(frontLeftMotor, backLeftMotor);
    	matchMotors(frontRightMotor, backRightMotor);
        //logger.log(constants.logging);
    	
    	gyroRate = gyro.getRate();
    	gyroAngle = gyro.getAngle();
    	SmartDashboard.putNumber("Gyro Rate", gyroRate);
    	SmartDashboard.putNumber("Gyro Angle", gyroAngle);
    	
    	ultrasonicValue = ultrasonic.getRangeInches();
        SmartDashboard.putNumber("UltrasonicDistance", ultrasonicValue);
    }
    public void arcadeDriveX(Joystick stick){
    	double move = stick.getY();
    	double turn = stick.getX();
    	arcadeDrive(move, turn);
    }
    public void arcadeDriveTwist(Joystick stick){
    	double move = stick.getY();
    	double rotate = stick.getTwist();
    	arcadeDrive(move,rotate);
    }
    public void slowArcadeDriveTwist(Joystick stick){
    	double move = stick.getY()*constants.slowDrivetrainSpeed;
    	double rotate = stick.getTwist()*constants.slowDrivetrainSpeed;
    	arcadeDrive(move,rotate);
    }
    public void shiftGears(){
    	if (rightShift.get() == HawkDoubleSolenoid.Value.kForward){
    		rightShift.set(HawkDoubleSolenoid.Value.kReverse);
    		gear = false;
    		
     	}else {
    		rightShift.set(HawkDoubleSolenoid.Value.kForward);
    		gear = true;
    	}
    	matchSolenoids();
    	SmartDashboard.putBoolean("Gear", gear);
    }
    private void matchSolenoids(){
    	leftShift.set(rightShift.get());
    	///logger.log(constants.logging);
    }
    public double getDistance(){
    	return ultrasonic.getRangeInches();
    }
    //autonCommands
    public void autonFastMoveForward(){
    	frontLeftMotor.set(constants.autonForward*constants.leftDrivetrainMultiplier);
    	backLeftMotor.set(constants.autonForward*constants.leftDrivetrainMultiplier);
    	frontRightMotor.set(constants.autonForward*constants.rightDrivetrainMultiplier);
    	backRightMotor.set(constants.autonForward*constants.rightDrivetrainMultiplier);
    }
    public void autonSlowMoveForward(){
    	frontLeftMotor.set(constants.autonSlowForward*constants.leftDrivetrainMultiplier);
    	backLeftMotor.set(constants.autonSlowForward*constants.leftDrivetrainMultiplier);
    	frontRightMotor.set(constants.autonSlowForward*constants.rightDrivetrainMultiplier);
    	backRightMotor.set(constants.autonSlowForward*constants.rightDrivetrainMultiplier);	
    }
    public void autonFastMoveBackward(){
    	frontLeftMotor.set(constants.autonBackward*constants.leftDrivetrainMultiplier);
    	backLeftMotor.set(constants.autonBackward*constants.leftDrivetrainMultiplier);
    	frontRightMotor.set(constants.autonBackward*constants.rightDrivetrainMultiplier);
    	backRightMotor.set(constants.autonBackward*constants.rightDrivetrainMultiplier);
    }
    public void autonSlowMoveBackward(){
    	frontLeftMotor.set(constants.autonSlowBackward*constants.leftDrivetrainMultiplier);
    	backLeftMotor.set(constants.autonSlowBackward*constants.leftDrivetrainMultiplier);
    	frontRightMotor.set(constants.autonSlowBackward*constants.rightDrivetrainMultiplier);
    	backRightMotor.set(constants.autonSlowBackward*constants.rightDrivetrainMultiplier);
    }
    public void autonTurnLeft(){
    	frontLeftMotor.set(constants.autonBackward*constants.leftDrivetrainMultiplier);
    	backLeftMotor.set(constants.autonBackward*constants.leftDrivetrainMultiplier);
    	frontRightMotor.set(constants.autonForward*constants.rightDrivetrainMultiplier);
    	backRightMotor.set(constants.autonForward*constants.rightDrivetrainMultiplier);
    }
    public void autonTurnRight(){
    	frontLeftMotor.set(constants.autonForward*constants.leftDrivetrainMultiplier);
    	backLeftMotor.set(constants.autonForward*constants.leftDrivetrainMultiplier);
    	frontRightMotor.set(constants.autonBackward*constants.rightDrivetrainMultiplier);
    	backRightMotor.set(constants.autonBackward*constants.rightDrivetrainMultiplier);
    }
    public void autonSlowTurnLeft(){
    	frontLeftMotor.set(constants.autonSlowBackward*constants.leftDrivetrainMultiplier);
    	backLeftMotor.set(constants.autonSlowBackward*constants.leftDrivetrainMultiplier);
    	frontRightMotor.set(constants.autonSlowForward*constants.rightDrivetrainMultiplier);
    	backRightMotor.set(constants.autonSlowForward*constants.rightDrivetrainMultiplier);
    }
    public void autonSlowTurnRight(){
    	frontLeftMotor.set(constants.autonSlowForward*constants.leftDrivetrainMultiplier);
    	backLeftMotor.set(constants.autonSlowForward*constants.leftDrivetrainMultiplier);
    	frontRightMotor.set(constants.autonSlowBackward*constants.rightDrivetrainMultiplier);
    	backRightMotor.set(constants.autonSlowBackward*constants.rightDrivetrainMultiplier);
    }
    public void autonStopMotors(){
    	frontLeftMotor.set(0);
    	backLeftMotor.set(0);
    	frontRightMotor.set(0);
    	backRightMotor.set(0);
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