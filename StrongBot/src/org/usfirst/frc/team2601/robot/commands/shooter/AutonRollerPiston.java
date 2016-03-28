package org.usfirst.frc.team2601.robot.commands.shooter;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonRollerPiston extends Command {

    public AutonRollerPiston(double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.combinedshooter);
    	setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.combinedshooter.autonShootRollers();
    	//if(Robot.combinedshooter.leftRollerMotor.get() == -1 && Robot.combinedshooter.rightRollerMotor.get() == 1){
    		Robot.combinedshooter.shootPiston();
    	//}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.combinedshooter.stopRollers();
    	Robot.combinedshooter.retractPiston();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
