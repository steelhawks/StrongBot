package org.usfirst.frc.team2601.robot.commands.autonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerIntake;
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
public class CrossLowBarShootTwoHighGoals extends CommandGroup {
    
    public  CrossLowBarShootTwoHighGoals() {
        
    	//addSequential(new AutonShooterPivotDown(0.4));
    	addSequential(new MoveToLowBar());
    	addSequential(new DriveFastForward(1.0));
    	addSequential(new AutonFastTurnRight(0.75));
    	//addSequential(new MovePivotToFire());
    	//addSequential(new AutonShooterPivotUp(0.4));
    	addSequential(new MoveToStart());
    	//Timer.delay(0.5);
    	addSequential(new AutonRollerShoot(0.5));
    	addSequential(new AutonRollerPiston(1.0));
    	//addSequential(new AutonShooterPivotDown(0.4));
    	addSequential(new MoveToLowBar());
    	addSequential(new AutonFastTurnLeft(0.75));
    	addSequential(new DriveFastBackward(1.0));
    	addParallel(new AutonFastTurnRight(1.5));
    	//addSequential(new AutonShooterPivotDown(0.3));
    	addSequential(new MoveToIntake());
    	addSequential(new AutonRollerIntake(1.0));
    	//addSequential(new AutonShooterPivotUp(0.7));
    	addSequential(new MoveToLowBar());
    	addSequential(new AutonFastTurnRight(1.5));
    	addSequential(new DriveFastForward(1.0));
    	addSequential(new AutonFastTurnRight(0.75));
    	//addSequential(new MovePivotToFire());
    	//addSequential(new AutonShooterPivotUp(0.2));
    	addSequential(new MoveToStart());
    	//Timer.delay(0.5);
		addSequential(new AutonRollerShoot(0.5));
    	addSequential(new AutonRollerPiston(1.0));
    }
}
