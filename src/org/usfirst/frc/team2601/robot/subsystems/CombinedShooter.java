package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.shooter.CombinedManualShooter;
import org.usfirst.frc.team2601.robot.util.HawkCANTalon;
import org.usfirst.frc.team2601.robot.util.HawkDoubleSolenoid;
import org.usfirst.frc.team2601.robot.util.HawkLoggable;
import org.usfirst.frc.team2601.robot.util.HawkLogger;
import org.usfirst.frc.team2601.robot.util.TalonEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
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
	HawkDoubleSolenoid shootForward = new HawkDoubleSolenoid(constants.shooterSolenoidOn, constants.shooterSolenoidOff, "shootingSolenoid");

	//CANTalons
	HawkCANTalon topRollerMotor = new HawkCANTalon(constants.topRollerTalon, "topRollerMotor");
	HawkCANTalon bottomRollerMotor = new HawkCANTalon(constants.bottomRollerTalon, "bottomRollerMotor");
	HawkCANTalon shooterPivotMotor = new HawkCANTalon(constants.shooterPivotTalon, "shooterPivotMotor");
	
	TalonEncoder shooterPivotEncoder = new TalonEncoder(shooterPivotMotor);
	
	AnalogInput ai = new AnalogInput(constants.shooterPivotPotAnalogPin);
	Potentiometer pot = new AnalogPotentiometer(ai,360,60);
	double degrees = pot.get();
	
	public double shooterPivotEncPosition;
	public double shooterPivotEncVelocity;
	
	public boolean shoot;
	//this is used for the logger
	private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
	public HawkLogger logger;
	public CombinedShooter(){
		//loggingList.add(shootForward);
		loggingList.add(topRollerMotor);
		loggingList.add(bottomRollerMotor);
		loggingList.add(shooterPivotMotor);
		loggingList.add(shootForward);
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
    	shootForward.set(HawkDoubleSolenoid.Value.kForward);
    	//logger.log(constants.logging);
    }
    
    public void shootPiston(){
    	shootForward.set(HawkDoubleSolenoid.Value.kForward);
    	
    }
    public void retractPiston(){
    	shootForward.set(HawkDoubleSolenoid.Value.kReverse);
    	//logger.log(constants.logging);
    }
    public void continuousPiston(){
    	Robot.combinedshooter.spinRollers();
    	Robot.combinedshooter.shootPiston();
    	Timer.delay(1);
		Robot.combinedshooter.stopRollers();
		Robot.combinedshooter.retractPiston();
    }
    public void manualControlShooter(Joystick stick){
    	double move = stick.getY();
    	topRollerMotor.set(move*constants.topRollerMultiplier*constants.rollerSpeed);
    	bottomRollerMotor.set(move*constants.bottomRollerMultiplier*constants.rollerSpeed);
    	if(stick.getY()<0){
    		topRollerMotor.set(move*constants.topRollerMultiplier*constants.intakeSpeed);
        	bottomRollerMotor.set(move*constants.bottomRollerMultiplier*constants.intakeSpeed);
    	}
    	//logger.log(constants.logging);
    }
    public void spinRollers(){
   		topRollerMotor.set(constants.rollerSpeed*constants.topRollerMultiplier);
   		bottomRollerMotor.set(constants.rollerSpeed*constants.bottomRollerMultiplier);
    	//logger.log(constants.logging);
    }
    public void stopRollers(){
    	topRollerMotor.set(0);
   		bottomRollerMotor.set(0);
    	//logger.log(constants.logging);
    }
    public void autonShootRollers(){
    	topRollerMotor.set(constants.autonShootSpeed*constants.topRollerMultiplier);
    	bottomRollerMotor.set(constants.autonShootSpeed*constants.bottomRollerMultiplier);
    }
    public void autonIntakeRollers(){
    	topRollerMotor.set(constants.autonIntakeSpeed*constants.topRollerMultiplier);
    	bottomRollerMotor.set(constants.autonIntakeSpeed*constants.bottomRollerMultiplier);
    }
    public void manualShooterPivot(Joystick stick){
    	double move = stick.getX();
    	shooterPivotMotor.set(move*constants.shooterPivotSpeed);
    	//Talon Encoders
    	shooterPivotEncPosition = shooterPivotEncoder.getPosition();
    	shooterPivotEncVelocity = shooterPivotEncoder.getVelocity();
    	
    	SmartDashboard.putNumber("ShooterPivotEncoderPosition",shooterPivotEncPosition);
    	SmartDashboard.putNumber("ShooterPivotEncoderVelocity",shooterPivotEncVelocity);
    	
    	//Actuator Potentiometers
    	SmartDashboard.putNumber("ShooterPivotPotentiometerDegree", degrees);
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

