package org.usfirst.frc.team2601.robot.commands.autonCommands.five;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Constants.Shoot_Auton;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRockWallShootHighGoalFive extends CommandGroup {

	Constants constants = Constants.getInstance();
	
    public  CrossRockWallShootHighGoalFive() {
        
    	addSequential(new DriveFastForward(2.0));
    	addSequential(new AutonFastTurnLeft(0.45));
    	Timer.delay(1.0);
    	if(constants.shootOrNot == Shoot_Auton.Yes){
    		addSequential(new AutonRollerShoot(0.5));
        	addSequential(new AutonRollerPiston(1.0));
    	}    	
    }
}
