package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.Scale;
import org.usfirst.frc.team2601.robot.util.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Scaler extends Subsystem {
    
	Constants constants = Constants.getInstance();
	
	HawkCANTalon armUpMotor = new HawkCANTalon(constants.armUpTalon, "armUpMotor");
	HawkCANTalon armScaleMotor = new HawkCANTalon(constants.scaleTalon, "armScaleMotor");
	

	//this is used for the logger
	private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
	public HawkLogger logger;
	
	public Scaler(){
		loggingList.add(armUpMotor);
		loggingList.add(armScaleMotor);

		//ready logger
		logger = new HawkLogger("scaler", loggingList);
		logger.setup();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new Scale());
    }
    public void manualArmUp(Joystick stick){
    	double move = -stick.getThrottle();
    	if(constants.scale){
    		armUpMotor.set(move*constants.scaleSpeed/*constants.scaleMultiplier*/);
    	}else{
    		armScaleMotor.set(move*constants.scaleSpeed/*constants.scaleMultiplier*/);
    	}
    	logger.log(constants.logging);
    }
    public void armUp(){
    	armUpMotor.set(constants.scaleSpeed/*constants.scaleMultiplier*/);
    	logger.log(constants.logging);
    }
    public void armScale(){
    	armScaleMotor.set(constants.scaleSpeed/*constants.scaleMultiplier*/);
    	logger.log(constants.logging);
    }
    
}

