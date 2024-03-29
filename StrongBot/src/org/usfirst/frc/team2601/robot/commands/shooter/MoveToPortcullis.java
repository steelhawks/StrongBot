package org.usfirst.frc.team2601.robot.commands.shooter;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveToPortcullis extends Command {

    public MoveToPortcullis() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.combinedshooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.combinedshooter.moveToPortcullis();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.combinedshooter.portcullis;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.combinedshooter.shooterPivotStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
