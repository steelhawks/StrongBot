package org.usfirst.frc.team2601.robot.commands.autonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonSlowTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonSlowTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotDown;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonTest extends CommandGroup {
    
    public  AutonTest() {
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
    	/*addSequential(new DriveFastForward(0.5));
    	addSequential(new DriveSlowForward(0.5));
    	addSequential(new DriveFastBackward(0.5));
    	addSequential(new DriveSlowBackward(0.5));
    	addSequential(new AutonFastTurnLeft(0.5));
    	addSequential(new AutonSlowTurnLeft(0.5));
    	addSequential(new AutonFastTurnRight(0.5));
    	addSequential(new AutonSlowTurnRight(0.5));*/
    	addSequential(new AutonRollerShoot(1.0));
    	addSequential(new AutonRollerIntake(1.0));
    	addSequential(new AutonShooterPivotUp(0.5));
    	addSequential(new AutonShooterPivotDown(0.5));
    	addSequential(new AutonPistonShoot(0.1));
    	addSequential(new AutonPistonRetract(0.1));
    }
}
