package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Claw_Type;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.shooter.CombinedManualShooter;
import org.usfirst.frc.team2601.robot.util.HawkCANTalon;
import org.usfirst.frc.team2601.robot.util.HawkDoubleSolenoid;
import org.usfirst.frc.team2601.robot.util.HawkLoggable;
import org.usfirst.frc.team2601.robot.util.HawkLogger;
import org.usfirst.frc.team2601.robot.util.TalonEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CombinedShooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Constants constants = Constants.getInstance();
	
	//Solenoids
	HawkDoubleSolenoid leftShootForward = new HawkDoubleSolenoid(constants.leftShooterSolenoidOn, constants.leftShooterSolenoidOff, "shootingSolenoid");
	HawkDoubleSolenoid rightShootForward = new HawkDoubleSolenoid(constants.rightShooterSolenoidOn, constants.rightShooterSolenoidOff, "shootingSolenoid");

	//CANTalons
	HawkCANTalon leftRollerMotor = new HawkCANTalon(constants.leftRollerTalon, "leftRollerMotor");
	HawkCANTalon rightRollerMotor = new HawkCANTalon(constants.rightRollerTalon, "rightRollerMotor");
	HawkCANTalon shooterPivotMotor = new HawkCANTalon(constants.shooterPivotTalon, "shooterPivotMotor");
	
	//TalonEncoder firstShooterEncoder = new TalonEncoder(leftRollerMotor); //left
	//TalonEncoder secondShooterEncoder = new TalonEncoder(rightRollerMotor); //right
	
	AnalogPotentiometer pot = new AnalogPotentiometer(constants.shooterPivotPotAnalogPin,360,60);
	
	public double firstShooterEncPosition;
	public double firstShooterEncVelocity;
	public double secondShooterEncPosition;
	public double secondShooterEncVelocity;
		
	double degrees;
	
	public boolean start;
	public boolean shoot;
	private boolean shot;
	public boolean autonShot;
	public boolean autonShotSecond;
	public boolean intake;
	public boolean lowBar;
	public boolean autonStart = false;
	//this is used for the logger
	private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
	public HawkLogger logger;
	public CombinedShooter(){
		//loggingList.add(shootForward);
		loggingList.add(leftRollerMotor);
		loggingList.add(rightRollerMotor);
		loggingList.add(shooterPivotMotor);
		//ready logger
		logger = new HawkLogger("combinedshooter", loggingList);
		logger.setup();
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new CombinedManualShooter());
    }
    public void manualShootPiston(Joystick stick){
    	manualControlShooter(stick);
    	leftShootForward.set(HawkDoubleSolenoid.Value.kForward);
    	if(constants.claw == Claw_Type.Circle){
    		rightShootForward.set(HawkDoubleSolenoid.Value.kForward);
    	}
    	//logger.log(constants.logging);
    }
    
    public void shootPiston(){
    	leftShootForward.set(HawkDoubleSolenoid.Value.kForward);
    	if(constants.claw == Claw_Type.Circle){
    		rightShootForward.set(HawkDoubleSolenoid.Value.kForward);
    	}
    	shot = true;
    	autonShot = true;
    	if(autonStart == true){
    		autonShotSecond = false;
    	}
    	SmartDashboard.putBoolean("Piston shot", shot);
    }
    public void retractPiston(){
    	leftShootForward.set(HawkDoubleSolenoid.Value.kReverse);
    	if(constants.claw == Claw_Type.Circle){
    		rightShootForward.set(HawkDoubleSolenoid.Value.kReverse);
    	}
    	shot = false;
    	autonShot = false;
    	if(autonStart == true){
    		autonShotSecond = true;
    	}
    	SmartDashboard.putBoolean("Piston shot", shot);
    }
    public void autonBoolean(){
    	autonStart = !autonStart;
    }
    public void continuousPiston(){
    	Robot.combinedshooter.spinRollers();
    	Robot.combinedshooter.shootPiston();
    	Timer.delay(1);
		Robot.combinedshooter.stopRollers();
		Robot.combinedshooter.retractPiston();
    }
    public void manualControlShooter(Joystick stick){
    	double move = stick.getTwist();
    	leftRollerMotor.set(move*constants.topRollerMultiplier*constants.rollerSpeed);
    	rightRollerMotor.set(move*constants.bottomRollerMultiplier*constants.rollerSpeed);
    	if(stick.getTwist()<0){
    		leftRollerMotor.set(move*constants.topRollerMultiplier*constants.intakeSpeed);
        	rightRollerMotor.set(move*constants.bottomRollerMultiplier*constants.intakeSpeed);
    	}
    	//Talon Encoders
    	//firstShooterEncPosition = firstShooterEncoder.getPosition();
    	//firstShooterEncVelocity = firstShooterEncoder.getVelocity();
    	
    	//secondShooterEncPosition = secondShooterEncoder.getPosition();
    	//secondShooterEncVelocity = secondShooterEncoder.getVelocity();
    	
    	//SmartDashboard.putNumber("LeftShooterEncoderPosition",firstShooterEncPosition);
    	//SmartDashboard.putNumber("LeftShooterEncoderVelocity",firstShooterEncVelocity);
    	//SmartDashboard.putNumber("RightShooterEncoderPosition",secondShooterEncPosition);
    	//SmartDashboard.putNumber("RightShooterEncoderVelocity",secondShooterEncVelocity);
    
    	//logger.log(constants.logging);
    }
    public void spinRollers(){
   		leftRollerMotor.set(constants.rollerSpeed*constants.topRollerMultiplier);
   		rightRollerMotor.set(constants.rollerSpeed*constants.bottomRollerMultiplier);
    	//logger.log(constants.logging);
    }
    public void stopRollers(){
    	leftRollerMotor.set(0);
   		rightRollerMotor.set(0);
    	//logger.log(constants.logging);
    }
    public void autonShootRollers(){
    	leftRollerMotor.set(constants.autonShootSpeed*constants.topRollerMultiplier);
    	rightRollerMotor.set(constants.autonShootSpeed*constants.bottomRollerMultiplier);
    }
    public void autonIntakeRollers(){
    	leftRollerMotor.set(constants.autonIntakeSpeed*constants.topRollerMultiplier);
    	rightRollerMotor.set(constants.autonIntakeSpeed*constants.bottomRollerMultiplier);
    }
    public void manualShooterPivot(Joystick stick){
    	//Actuator Potentiometers
    	SmartDashboard.putNumber("ShooterPivotPotentiometerDegree", degrees);   
	    double move = stick.getY();
	    degrees = pot.get();
	    /*if (move > 0){ //stickback
	    	if(degrees>constants.minPot){
	        	shooterPivotMotor.set(-move*constants.shooterPivotSpeed);
	   		}
	   		else{
	   			shooterPivotMotor.set(0);
	    	}
	   	}
	    if (move < 0){ //stickforward
	   		if (degrees < constants.maxPot){
	   			shooterPivotMotor.set(-move*constants.shooterPivotSpeed);
		   	}
	    	else{
	    		shooterPivotMotor.set(0);
	    	}
	    } */
	    if((move > 0 && degrees < constants.minPot) || (move < 0 && degrees > constants.maxPot)){
	    	shooterPivotMotor.set(0.00);
	    }else{
			shooterPivotMotor.set(-move*constants.shooterPivotSpeed);
	    }
    }
    public void moveToFire(){
    	degrees = pot.get();
    	if(degrees >= constants.shootPot - constants.potTolerance && degrees <= constants.shootPot + constants.potTolerance){
    		shooterPivotMotor.set(0); 
    		shoot = true;
    	}
    	else if(degrees > constants.shootPot + constants.potTolerance){
    		shooterPivotMotor.set(-constants.buttonPivotSpeed);
    		shoot = false;
    	}
    	else if(degrees < constants.shootPot - constants.potTolerance){
    		shooterPivotMotor.set(constants.buttonPivotSpeed);
    		shoot = false;
    	}
		SmartDashboard.putBoolean("PivotShoot", shoot);
    }
    public void moveToStart(){
    	degrees = pot.get();
    	if(degrees >= constants.startPot - constants.potTolerance && degrees <= constants.startPot + constants.potTolerance){
    		shooterPivotMotor.set(0); 
    		start = true;
    	}
    	else if(degrees > constants.startPot + constants.potTolerance){
    		shooterPivotMotor.set(-constants.buttonPivotSpeed);
    		start = false;
    	}
    	else if(degrees < constants.startPot - constants.potTolerance){
    		shooterPivotMotor.set(constants.buttonPivotSpeed);
    		start = false;
    	}
		SmartDashboard.putBoolean("StartConfig", start);
    }
    public void moveToIntake(){
    	degrees = pot.get();
    	if(degrees >= constants.intakePot - constants.potTolerance && degrees <= constants.intakePot + constants.potTolerance){
    		shooterPivotMotor.set(0); 
    		intake = true;
    	}
    	else if(degrees > constants.intakePot + constants.potTolerance){
    		shooterPivotMotor.set(-constants.buttonPivotSpeed);
    		intake = false;
    	}
    	else if(degrees < constants.intakePot - constants.potTolerance){
    		shooterPivotMotor.set(constants.buttonPivotSpeed);
    		intake = false;
    	}
		SmartDashboard.putBoolean("PivotIntake", intake);
    }
    public void moveToLowBar(){
    	degrees = pot.get();
    	if(degrees >= constants.lowBarPot - constants.potTolerance && degrees <= constants.lowBarPot + constants.potTolerance){
    		shooterPivotMotor.set(0); 
    		lowBar = true;
    	}
    	else if(degrees > constants.lowBarPot + constants.potTolerance){
    		shooterPivotMotor.set(-constants.buttonPivotSpeed);
    		lowBar = false;
    	}
    	else if(degrees < constants.lowBarPot - constants.potTolerance){
    		shooterPivotMotor.set(constants.buttonPivotSpeed);
    		lowBar = false;
    	}
		SmartDashboard.putBoolean("PivotLowBar", lowBar);
    }
    public void shooterPivotUp(){
    	shooterPivotMotor.set(constants.shooterPivotSpeed*constants.shooterPivotUpMultiplier); 
    }
    public void shooterPivotDown(){
    	shooterPivotMotor.set(constants.shooterPivotSpeed*constants.shooterPivotDownMultiplier);
    }
    public void shooterPivotStop(){
    	shooterPivotMotor.set(0);
    }
    public void autonShooterPivotUp(){
    	shooterPivotMotor.set(constants.autonPivotSpeed*constants.shooterPivotUpMultiplier); 
    }
    public void autonShooterPivotDown(){
    	shooterPivotMotor.set(constants.autonPivotSpeed*constants.shooterPivotDownMultiplier);
    }
    public void autonSlowShooterPivotUp(){
    	shooterPivotMotor.set(constants.autonSlowPivotSpeed*constants.shooterPivotUpMultiplier);
    }
    public void autonSlowShooterPivotDown(){
    	shooterPivotMotor.set(constants.autonSlowPivotSpeed*constants.shooterPivotDownMultiplier);
    }
}

