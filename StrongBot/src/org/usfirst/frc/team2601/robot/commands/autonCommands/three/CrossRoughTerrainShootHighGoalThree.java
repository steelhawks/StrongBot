package org.usfirst.frc.team2601.robot.commands.autonCommands.three;

import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MovePivotToFire;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRoughTerrainShootHighGoalThree extends CommandGroup {
    
    public  CrossRoughTerrainShootHighGoalThree() {
        
    	addSequential(new DriveFastForward(2.5));
    	//addSequential(new MovePivotToFire());
    	Timer.delay(1.0);
    	addParallel(new AutonPistonShoot(2.0));
    	addSequential(new AutonRollerShoot(2.0));
    	addSequential(new AutonPistonRetract(0.2));
       }
}
