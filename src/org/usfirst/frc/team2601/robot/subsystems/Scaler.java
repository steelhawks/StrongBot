package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.scaler.Scale;
import org.usfirst.frc.team2601.robot.util.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Scaler extends Subsystem {
    
	Constants constants = Constants.getInstance();
	
	HawkCANTalon armUpMotor = new HawkCANTalon(constants.armUpTalon, "armUpMotor");
	HawkCANTalon armExtendMotor = new HawkCANTalon(constants.armExtendTalon, "armExtendMotor");
	HawkCANTalon armRetractMotor = new HawkCANTalon(constants.armRetractTalon, "armRetractMotor");

	//this is used for the logger
	private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
	public HawkLogger logger;
	private static boolean scale = true;
	
	public Scaler(){
		loggingList.add(armUpMotor);
		loggingList.add(armExtendMotor);
		loggingList.add(armRetractMotor);

		//ready logger
		logger = new HawkLogger("scaler", loggingList);
		logger.setup();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Scale());
    }
    public void scaleBoolean(){
    	scale = !scale;
    }
    public void manualScale(Joystick stick){
    	double move = stick.getTwist();
    	if(scale){
    		armExtendMotor.set(move*constants.scaleSpeed/*constants.scaleMultiplier*/);
    	}else{
    		armRetractMotor.set(-move*constants.scaleSpeed/*constants.scaleMultiplier*/);
    	}
    	logger.log(constants.logging);
    }
    public void manualArmUp(Joystick stick){
    	double move = stick.getThrottle();
    	armUpMotor.set(move*constants.armUpSpeed);
    }
    public void armUp(){
    	armUpMotor.set(constants.scaleSpeed/*constants.scaleMultiplier*/);
    	logger.log(constants.logging);
    }
    public void armExtend(){
    	armExtendMotor.set(constants.scaleSpeed);
    }
    public void armScale(){
    	armRetractMotor.set(constants.scaleSpeed/*constants.scaleMultiplier*/);
    	logger.log(constants.logging);
    }
    
}

