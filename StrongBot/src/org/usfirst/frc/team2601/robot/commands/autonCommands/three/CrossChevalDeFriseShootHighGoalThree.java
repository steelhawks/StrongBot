package org.usfirst.frc.team2601.robot.commands.autonCommands.three;

import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotDown;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFriseShootHighGoalThree extends CommandGroup {
    
    public  CrossChevalDeFriseShootHighGoalThree() {
        
    	addSequential(new DriveSlowForward(0.25));
    	addSequential(new AutonShooterPivotDown(0.3));
    	Timer.delay(0.1);
    	addSequential(new DriveFastForward(0.75));
    	addSequential(new AutonShooterPivotUp(0.3));
    	//addSequential(new MovePivotToFire());
    	addSequential(new AutonFastTurnRight(0.1));
    	Timer.delay(1.0);
		addSequential(new AutonRollerShoot(0.5));
    	addSequential(new AutonRollerPiston(1.0));
    }
}
