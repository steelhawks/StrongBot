package org.usfirst.frc.team2601.robot.commands.autonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GyroForward extends CommandGroup {
    
    public  GyroForward() {
        addSequential(new AutonGyroForward(10.0));
    }
}
