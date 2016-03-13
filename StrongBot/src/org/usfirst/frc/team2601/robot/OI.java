package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.Constants.Test_Mode;
import org.usfirst.frc.team2601.robot.commands.ReadCorners;
import org.usfirst.frc.team2601.robot.commands.SlowDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroReset;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Shift;
import org.usfirst.frc.team2601.robot.commands.drivetrain.StopMotors;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutoAlign;
import org.usfirst.frc.team2601.robot.commands.scaler.GrapplingHookPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.ContinuousPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.ContinuousShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.EStopShoot;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToLowBar;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToStart;
import org.usfirst.frc.team2601.robot.commands.shooter.ShooterPiston;
import org.usfirst.frc.team2601.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    
	Constants constants = Constants.getInstance();
		
	public OI(){
		constants.stick = new Joystick(constants.joystickPort);
	    constants.shooter = new Joystick(constants.secondJoystickPort);
	
	    //Driver Controls
	    //if (constants.mode == Constants.System_Mode.All ||
	   // 		constants.mode == Constants.System_Mode.DriveOnly) {
	    	Button shift = new JoystickButton(constants.stick, constants.shiftButton);
	    	shift.whenPressed(new Shift());
	    	
	    	Button slowDrivetrain = new JoystickButton(constants.stick, constants.slowDrivetrainButton);
	    	slowDrivetrain.whileHeld(new SlowDrive());
	    	
	    	Button gyroReset = new JoystickButton(constants.stick, constants.gyroResetButton);
	    	gyroReset.whenPressed(new GyroReset());
	    	
	    	Button vision = new JoystickButton(constants.stick, constants.vision);
	    	vision.whenPressed(new ReadCorners());
	    	
	    	Button stop = new JoystickButton(constants.stick, constants.stopMotors);
	    	stop.whenPressed(new StopMotors());
	    	
	    	Button align = new JoystickButton(constants.stick, constants.alignButton);
	    	align.whenPressed(new AutoAlign());
	    	
	    	if(constants.test == constants.test.MarcusWeirdTest){
	    		Button driverContinuousWheels = new JoystickButton(constants.stick, constants.driverContinouousWheelsButton);
	    		driverContinuousWheels.whenPressed(new ContinuousShoot());
	    		
	    		Button driverPiston = new JoystickButton(constants.stick, constants.driverPistonButton);
	    		driverPiston.whenPressed(new ContinuousPiston());	
	    	}
	    //}
	    
	    //Operator Controls
	    // (constants.mode == Constants.System_Mode.All ||
	    	//	constants.mode == Constants.System_Mode.ShootOnly) {
	    	Button triggerShooter = new JoystickButton(constants.shooter, constants.triggerShooterButton);
	    	triggerShooter.whenPressed(new ContinuousPiston());
	    	
	    	Button piston = new JoystickButton(constants.shooter, constants.pistonButton);
	    	piston.whenPressed(new ShooterPiston());
	    
	    	Button continuousWheels = new JoystickButton(constants.shooter, constants.continuousWheelsButton);
	    	continuousWheels.whenPressed(new ContinuousShoot());
	    	
	    	Button stopShoot= new JoystickButton(constants.shooter, constants.stopShootButton);
	    	stopShoot.whenPressed(new EStopShoot());
	    	
	    	//Button moveToFire = new JoystickButton(constants.shooter, constants.moveToFireButton);
	    	//moveToFire.whenPressed(new MoveToFire());
	    	
	    	Button moveToStart = new JoystickButton(constants.shooter, constants.moveToStartButton);
	    	moveToStart.whenPressed(new MoveToStart());
	    	
	    	Button moveToIntake = new JoystickButton(constants.shooter, constants.intakeButton);
	    	moveToIntake.whenPressed(new MoveToIntake());
	    	
	    	Button moveToLB = new JoystickButton(constants.shooter, constants.lowBarButton);
	    	moveToLB.whenPressed(new MoveToLowBar());
	    	
	    	//Button grapplingHook = new JoystickButton(constants.shooter, constants.grapplingHook);
	    	//grapplingHook.whenPressed(new GrapplingHookPiston());
	   // }
	}
}
