package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.shooter.CombinedManualShooter;
import org.usfirst.frc.team2601.robot.util.HawkCANTalon;
import org.usfirst.frc.team2601.robot.util.HawkDoubleSolenoid;
import org.usfirst.frc.team2601.robot.util.HawkLoggable;
import org.usfirst.frc.team2601.robot.util.HawkLogger;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

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
    	//setDefaultCommand(new CombinedManualShooter());
    }
    public void manualShootPiston(Joystick stick){
    	manualControlShooter(stick);
    	shootForward.set(HawkDoubleSolenoid.Value.kForward);
    	//logger.log(constants.logging);
    }
    
    public void shootPiston(){
    	spinRollers();
    	shootForward.set(HawkDoubleSolenoid.Value.kForward);
    }
    public void retractPiston(){
    	shootForward.set(HawkDoubleSolenoid.Value.kReverse);
    	//logger.log(constants.logging);
    }
    public void manualControlShooter(Joystick stick){
    	double move = stick.getY();
    	topRollerMotor.set(move*constants.topRollerMultiplier*constants.rollerSpeed);
    	bottomRollerMotor.set(move*constants.bottomRollerMultiplier*constants.rollerSpeed);
    	if(stick.getY()<0){
    		topRollerMotor.set(move*constants.topRollerMultiplier*constants.intakeSpeed);
        	bottomRollerMotor.set(move*constants.bottomRollerMultiplier*constants.intakeSpeed);
    	}
    	logger.log(constants.logging);
    }
    public void spinRollers(){
   		topRollerMotor.set(constants.rollerSpeed*constants.topRollerMultiplier);
   		bottomRollerMotor.set(constants.rollerSpeed*constants.bottomRollerMultiplier);
    	logger.log(constants.logging);
    }
    public void stopRollers(){
    	topRollerMotor.set(constants.stopRollerSpeed*constants.topRollerMultiplier);
   		bottomRollerMotor.set(constants.stopRollerSpeed*constants.bottomRollerMultiplier);
    	logger.log(constants.logging);
    }
    public void manualShooterPivot(Joystick stick){
    	double move = stick.getTwist();
    	shooterPivotMotor.set(move*constants.shooterPivotSpeed);
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
}

