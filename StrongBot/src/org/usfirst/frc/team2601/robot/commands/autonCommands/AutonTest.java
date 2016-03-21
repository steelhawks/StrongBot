package org.usfirst.frc.team2601.robot.commands.autonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonBackwardEncoder;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonDrivetrainToDriverStation;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonFastTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonForwardEncoder;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroCorrection;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonSlowTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonSlowTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurnLeftToLowGoal;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurnRightToLowGoal;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveSlowForward;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonRetract;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonPistonShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShooterWait;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShootBoolean;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotDown;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonShooterPivotUp;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToLowBar;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToStart;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonTest extends CommandGroup {
    
    public  AutonTest() {

    	addSequential(new AutonForwardEncoder());
    	addSequential(new AutonBackwardEncoder());
    	addSequential(new AutonGyroCorrection());
    	addSequential(new GyroForward());
    }
}
