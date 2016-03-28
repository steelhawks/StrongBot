package org.usfirst.frc.team2601.robot.commands.autonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonBackwardGyro;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonLowBarForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurnRightToLowBar;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Delay;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToLowBar;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootHighGoalCrossLowBar extends CommandGroup {
    
    public  ShootHighGoalCrossLowBar() {

    	addSequential(new MoveToFire());
    	addSequential(new AutonRollerIntake(1.0));
    	addSequential(new Delay(0.5));
    	addSequential(new AutonRollerShoot(0.25));
    	addSequential(new AutonRollerPiston(1.0));
    	addSequential(new AutonTurnRightToLowBar());
    	addSequential(new MoveToLowBar());
    	addSequential(new AutonBackwardGyro(3.0));
    	addSequential(new AutonLowBarForward(2.5));
    }
}
