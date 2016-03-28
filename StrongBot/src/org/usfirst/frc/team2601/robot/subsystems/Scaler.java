package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.scaler.Scale;
import org.usfirst.frc.team2601.robot.util.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Scaler extends Subsystem {
    
	Constants constants = Constants.getInstance();
	//not used
	/*HawkCANTalon armUpMotor = new HawkCANTalon(30, "armUpMotor");
	HawkCANTalon armExtendMotor = new HawkCANTalon(21, "armExtendMotor");
	HawkCANTalon armRetractMotor = new HawkCANTalon(20, "armRetractMotor");*/
	
	//used
	HawkCANTalon winchMotor = new HawkCANTalon(constants.winchTalon, "winchMotor");
	
	HawkDoubleSolenoid shootHook = new HawkDoubleSolenoid(constants.scalerSolenoidOn,constants.scalerSolenoidOff, "ScalerSolenoid");
	
	//this is used for the logger
	private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
	public HawkLogger logger;
	private static boolean scale = true;
	
	public Scaler(){
		/*loggingList.add(armUpMotor);
		loggingList.add(armExtendMotor);
		loggingList.add(armRetractMotor);*/

		//ready logger
		//logger = new HawkLogger("scaler", loggingList);
		//logger.setup();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Scale());
    }
    public void scaleBoolean(){
    	scale = !scale;
    }
    public void winchUp(Joystick stick){//twist brings winch up
    	double move = stick.getX();
    	winchMotor.set(move*constants.scaleSpeed);
    }
    public void shootGrapplingHook(){
    	shootHook.set(HawkDoubleSolenoid.Value.kForward);
    }
    public void retractGrapplingHook(){
    	shootHook.set(HawkDoubleSolenoid.Value.kReverse);
    }
    
    //not used
    /*public void manualScale(Joystick stick){
    	double move = stick.getTwist();
    	if(scale){
    		armExtendMotor.set(move*constants.scaleSpeed/*constants.scaleMultiplier);
    	}else{
    		armRetractMotor.set(-move*constants.scaleSpeed/*constants.scaleMultiplier);
    	}
    	logger.log(constants.logging);
    }
    public void manualArmUp(Joystick stick){
    	double move = stick.getThrottle();
    	armUpMotor.set(move*constants.armUpSpeed);
    }
    public void armUp(){
    	armUpMotor.set(constants.scaleSpeed/*constants.scaleMultiplier);
    	logger.log(constants.logging);
    }
    public void armExtend(){
    	armExtendMotor.set(constants.scaleSpeed);
    }
    public void armScale(){
    	armRetractMotor.set(constants.scaleSpeed/*constants.scaleMultiplier);
    	logger.log(constants.logging);
    }*/
}

