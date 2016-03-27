package org.usfirst.frc.team2601.robot.commands.autonCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Shoot_Auton;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonDrivetrainToDriverStation;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonDrivetrainToDriverStationHighGoal;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroForwardDriverStation;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonLowBarForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurnRightToHighGoal;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurnRightToLowGoal;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Delay;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotDown;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToLowBar;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToStart;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLowBarShootHighGoal extends CommandGroup {
    
	Constants constants = Constants.getInstance();
	
    public  CrossLowBarShootHighGoal() {
    
    	addSequential(new MoveToLowBar());
    	addSequential(new AutonLowBarForward(1.75));
    	addSequential(new MoveToStart());
    	//Timer.delay(1.0);
    	//addSequential(new AutonDrivetrainToDriverStation());
    	addSequential(new AutonGyroForwardDriverStation());
    	//addSequential(new AutonGyroCorrection());
    	//Timer.delay(1.0);
    	//addSequential(new AutonFastTurnRight(0.36));//0.5 90 degreee turn
    	addSequential(new AutonTurnRightToHighGoal());
    	addSequential(new DriveSlowBackward(0.25));
    	addSequential(new MoveToFire());
    	addSequential(new AutonRollerIntake(1.0));
    	addSequential(new Delay(1.0));
    	addSequential(new AutonRollerShoot(0.25));
    	addSequential(new AutonRollerPiston(1.0));
    }
}
