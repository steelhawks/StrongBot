package org.usfirst.frc.team2601.robot.commands.shooter;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ContinuousShoot extends Command {
	Constants constants = Constants.getInstance();
	
	
	public ContinuousShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.roller);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.retractPiston();
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.roller.spinRollers();
    	constants.shot = false;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(constants.roll){//is turned into constants.roll == true in the ShooterPiston command
    		Timer.delay(1);
    		Robot.roller.stopRollers();
    		Robot.shooter.retractPiston();
    		constants.roll = false;
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
