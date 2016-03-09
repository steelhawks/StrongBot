package org.usfirst.frc.team2601.robot.commands.autonCommands.five;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Shoot_Auton;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotDown;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MovePivotToFire;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFriseShootHighGoalFive extends CommandGroup {
    
	Constants constants = Constants.getInstance();
	
    public  CrossChevalDeFriseShootHighGoalFive() {
           	
    	addSequential(new DriveSlowForward(0.25));
    	addSequential(new AutonShooterPivotDown(0.3));
    	Timer.delay(0.1);
    	addSequential(new DriveFastForward(0.75));
    	addSequential(new AutonShooterPivotUp(0.3));
    	//addSequential(new MovePivotToFire());
    	addSequential(new AutonFastTurnLeft(0.7));
    	Timer.delay(1.0);
    	if(constants.shootOrNot == Shoot_Auton.Yes){
    		addParallel(new AutonRollerShoot(2.0));
    		addSequential(new AutonPistonShoot(2.0));
    		addSequential(new AutonPistonRetract(0.2));
    	}
    }
}
