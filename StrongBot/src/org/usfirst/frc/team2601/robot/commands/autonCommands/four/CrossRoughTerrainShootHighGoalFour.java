package org.usfirst.frc.team2601.robot.commands.autonCommands.four;

import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRoughTerrainShootHighGoalFour extends CommandGroup {
    
    public  CrossRoughTerrainShootHighGoalFour() {
        
    	addSequential(new AutonShooterPivotUp(0.5));
    	addSequential(new DriveFastForward(2.5));
    	//addSequential(new AutonShooterPivotUp(0.85));
    	addSequential(new MoveToFire());
		addSequential(new AutonRollerShoot(0.5));
    	addSequential(new AutonRollerPiston(1.0));
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
