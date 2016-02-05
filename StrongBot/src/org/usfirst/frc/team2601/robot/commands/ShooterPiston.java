package org.usfirst.frc.team2601.robot.commands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.util.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterPiston extends Command {
	
	Constants constants = Constants.getInstance();

	HawkCANTalon topRollerMotor = new HawkCANTalon(constants.topRollerTalon, "topRollerMotor");
	HawkCANTalon bottomRollerMotor = new HawkCANTalon(constants.bottomRollerTalon, "bottomRollerMotor");
	
    public ShooterPiston() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(constants.shot){
    		Robot.shooter.retractPiston();
    	}
    	if(!constants.shot){
    		Robot.shooter.shootPiston();
    		if(topRollerMotor.get() == constants.topRollerMultiplier && bottomRollerMotor.get() == constants.bottomRollerMultiplier){
    			constants.roll = true;//retracts the piston in the Continuous Rollers command
    		}
    	}
    } 
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	constants.shot = !constants.shot;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
