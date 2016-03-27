package org.usfirst.frc.team2601.robot.commands.autonCommands.five;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Shoot_Auton;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroForwardDriverStation;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonLowBarForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurnLeftToLowGoal;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurnRightToLowGoal;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Delay;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotDown;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToLowBar;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToStart;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFriseShootHighGoalFive extends CommandGroup {
    
	Constants constants = Constants.getInstance();
	
    public  CrossChevalDeFriseShootHighGoalFive() {
 
    	addSequential(new AutonGyroForward(1.0));
    	addSequential(new MoveToIntake());
    	addSequential(new AutonLowBarForward(0.5));
    	addParallel(new MoveToStart());
    	//addSequential(new DriveSlowForward(0.25));
    	addSequential(new AutonLowBarForward(1.5));
    	//addSequential(new AutonGyroForwardDriverStation());
    	//addSequential(new AutonShooterPivotUp(0.3));
    	//addSequential(new MovePivotToFire());
    	//addSequential(new AutonFastTurnLeft(0.7));
    	//Timer.delay(1.0);
    	addSequential(new MoveToFire());
    	//addSequential(new AutonTurnLeftToLowGoal());
    	addSequential(new Delay(1.5));
    	addSequential(new AutonRollerShoot(0.5));
       	addSequential(new AutonRollerPiston(1.0));
    }
}
