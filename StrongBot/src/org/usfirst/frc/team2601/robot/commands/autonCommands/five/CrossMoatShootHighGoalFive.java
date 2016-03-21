package org.usfirst.frc.team2601.robot.commands.autonCommands.five;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Shoot_Auton;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonDrivetrainToDriverStation;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurnLeftToLowGoal;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToLowBar;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToStart;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossMoatShootHighGoalFive extends CommandGroup {
    
	Constants constants = Constants.getInstance();
	
    public  CrossMoatShootHighGoalFive() {
       
    	addSequential(new MoveToStart());
    	addSequential(new DriveFastForward(1.0));
    	addSequential(new AutonDrivetrainToDriverStation());
    	//Timer.delay(1.0);
    	addSequential(new MoveToLowBar());
    	//addSequential(new AutonFastTurnLeft(0.36));//0.5 90 degreee turn
    	addSequential(new AutonTurnLeftToLowGoal());
    	addSequential(new DriveSlowForward(1.25));
    	//if(constants.shootOrNot == Shoot_Auton.Yes){
    	addSequential(new AutonRollerShoot(0.25));
    	addSequential(new AutonRollerPiston(1.0));
    }
}
