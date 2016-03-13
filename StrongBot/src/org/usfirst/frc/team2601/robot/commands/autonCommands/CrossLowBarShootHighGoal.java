package org.usfirst.frc.team2601.robot.commands.autonCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Shoot_Auton;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotDown;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBarShootHighGoal extends CommandGroup {
    
	Constants constants = Constants.getInstance();
	
    public  CrossLowBarShootHighGoal() {
    	
    	/*addParallel(new DriveSlowForward(1.0));
    	addSequential(new AutonRollerIntake(0.1));
    	Timer.delay(0.1);*/
    	//addSequential(new AutonShooterPivotUp(0.2));
    	addSequential(new AutonShooterPivotDown(1.07));
    	addSequential(new DriveFastForward(1.25));
    	addSequential(new AutonFastTurnRight(0.45));
    	//addSequential(new MovePivotToFire());
    	addSequential(new AutonShooterPivotUp(1.0));
    	Timer.delay(0.1);
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
