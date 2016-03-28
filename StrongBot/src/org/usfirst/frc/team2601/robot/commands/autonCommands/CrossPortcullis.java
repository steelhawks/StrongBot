package org.usfirst.frc.team2601.robot.commands.autonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonLowBarForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Delay;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.AutonRollerShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToPortcullis;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToStart;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossPortcullis extends CommandGroup {
    
    public  CrossPortcullis() {
        
    	addSequential(new AutonGyroForward(1.0));
    	addSequential(new MoveToPortcullis());
    	addSequential(new AutonLowBarForward(0.5));
    	addParallel(new MoveToStart());
    	addSequential(new AutonLowBarForward(1.5));
    }
}
