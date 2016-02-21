package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
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
    	//setDefaultCommand(new ManualShoot());
    }
    public void manualControlShooter(Joystick shooter){
    	double move = shooter.getY();
    	topRollerMotor.set(move*constants.topRollerMultiplier*constants.rollerSpeed);
    	bottomRollerMotor.set(move*constants.bottomRollerMultiplier*constants.rollerSpeed);
    	if(shooter.getY()<0){
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
    public void autonShootRollers(){
    	topRollerMotor.set(constants.autonShootSpeed*constants.topRollerMultiplier);
    	bottomRollerMotor.set(constants.autonShootSpeed*constants.bottomRollerMultiplier);
    }
    public void autonIntakeRollers(){
    	topRollerMotor.set(constants.autonIntakeSpeed*constants.topRollerMultiplier);
    	bottomRollerMotor.set(constants.autonIntakeSpeed*constants.bottomRollerMultiplier);
    }

    public void stopRollers(){
    	topRollerMotor.set(0);
   		bottomRollerMotor.set(0);
    	//logger.log(constants.logging);
    }

}

