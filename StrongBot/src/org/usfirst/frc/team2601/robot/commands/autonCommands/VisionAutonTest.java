package org.usfirst.frc.team2601.robot.commands.autonCommands;

import org.usfirst.frc.team2601.robot.commands.ReadCorners;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonVisionAligning;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DriveFastForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionAutonTest extends CommandGroup {
    
    public  VisionAutonTest() {
    	
    	addSequential(new DriveFastForward(2.0));
    	addSequential(new AutonVisionAligning());
    	
    	
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
