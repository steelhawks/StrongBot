package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.Shoot;
import org.usfirst.frc.team2601.robot.util.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterRoller extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Constants constants = Constants.getInstance();
	
	HawkCANTalon topRollerMotor = new HawkCANTalon(constants.topRollerTalon, "topRollerMotor");
	HawkCANTalon bottomRollerMotor = new HawkCANTalon(constants.bottomRollerTalon, "bottomRollerMotor");
	
	//this is used for the logger
		private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
		public HawkLogger logger;
		
	public ShooterRoller() {
		loggingList.add(topRollerMotor);
		loggingList.add(bottomRollerMotor);
		
		//ready logger
				logger = new HawkLogger("shooterroller", loggingList);
				logger.setup();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Shoot());
    }
    public void manualControlShooter(Joystick stick){
    	double move = stick.getY();
    	topRollerMotor.set(move*constants.topRollerMultiplier*constants.rollerSpeed);
    	bottomRollerMotor.set(move*constants.bottomRollerMultiplier*constants.rollerSpeed);
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

}

