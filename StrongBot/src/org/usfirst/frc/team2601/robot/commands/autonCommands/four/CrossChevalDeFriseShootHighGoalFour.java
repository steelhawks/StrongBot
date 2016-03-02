package org.usfirst.frc.team2601.robot.commands.autonCommands.four;

import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotDown;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFriseShootHighGoalFour extends CommandGroup {
    
    public  CrossChevalDeFriseShootHighGoalFour() {
        
    	addParallel(new AutonShooterPivotUp(0.75));
    	addSequential(new DriveSlowForward(0.7));
    	addSequential(new AutonShooterPivotDown(0.5));
    	Timer.delay(0.1);
    	addSequential(new DriveFastForward(2.0));
    	addSequential(new AutonShooterPivotUp(0.8));
    	Timer.delay(0.1);
    	addParallel(new AutonRollerShoot(2.0));
    	addSequential(new AutonPistonShoot(2.0));
    	addSequential(new AutonPistonRetract(0.2));
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
