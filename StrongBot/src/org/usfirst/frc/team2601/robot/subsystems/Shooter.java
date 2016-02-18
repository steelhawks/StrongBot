package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.shooter.ShooterPiston;
import org.usfirst.frc.team2601.robot.util.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Constants constants = Constants.getInstance();
	
	HawkDoubleSolenoid shootForward = new HawkDoubleSolenoid(constants.shooterSolenoidOn, constants.shooterSolenoidOff, "shootingSolenoid");
	//DoubleSolenoid shootForward = new DoubleSolenoid(constants.shooterSolenoidOn, constants.shooterSolenoidOff);

	//this is used for the logger
	private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
	public HawkLogger logger;
	
	public Shooter(){
	//loggingList.add(shootForward);
	
	//ready logger
			logger = new HawkLogger("shooter", loggingList);
			logger.setup();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//you can't set this as default
    }
    public void shootPiston(){
    	shootForward.set(HawkDoubleSolenoid.Value.kForward);
    	//logger.log(constants.logging);
    }
    public void retractPiston(){
    	shootForward.set(HawkDoubleSolenoid.Value.kReverse);
    	//logger.log(constants.logging);
    }
}
    


