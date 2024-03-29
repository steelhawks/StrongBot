package org.usfirst.frc.team2601.robot.commands.autonCommands.three;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Shoot_Auton;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossMoatShootHighGoalThree extends CommandGroup {
    
	Constants constants = Constants.getInstance();
    
    public  CrossMoatShootHighGoalThree() {
        
    	addSequential(new DriveFastForward(2.5));
    	//addSequential(new MovePivotToFire());
    	Timer.delay(1.0);
    	if(constants.shootOrNot == Shoot_Auton.Yes){
	    	addSequential(new AutonRollerShoot(1.0));
	    	addParallel(new AutonRollerShoot(1.5));
	    	addSequential(new AutonPistonShoot(1.5));
	    	addSequential(new AutonPistonRetract(0.2));
    	}    	
    	
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
