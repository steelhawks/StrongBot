package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.commands.ReadGrip;
import org.usfirst.frc.team2601.robot.commands.SlowDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Shift;
import org.usfirst.frc.team2601.robot.commands.scaler.ScaleBoolean;
import org.usfirst.frc.team2601.robot.commands.shooter.ContinuousShoot;
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
	
	    //if (constants.mode == Constants.System_Mode.All ||
	   // 		constants.mode == Constants.System_Mode.DriveOnly) {
	    	Button shift = new JoystickButton(constants.stick, constants.shiftButton);
	    	shift.whenPressed(new Shift());
	    //}
	    
	    // (constants.mode == Constants.System_Mode.All ||
	    	//	constants.mode == Constants.System_Mode.ShootOnly) {
	    	Button triggerShooter = new JoystickButton(constants.shooter, constants.triggerShooterButton);
	    	triggerShooter.whenPressed(new ShooterPiston());
	    
	    	Button continuousWheels = new JoystickButton(constants.shooter, constants.continuousWheelsButton);
	    	continuousWheels.whenPressed(new ContinuousShoot());
	    
	    	Button switchScaleMotors = new JoystickButton(constants.shooter, constants.switchScaleMotorsButton);
	    	switchScaleMotors.whenPressed(new ScaleBoolean());
	    	
	    	Button slowDrivetrain = new JoystickButton(constants.stick, constants.slowDrivetrainButton);
	    	slowDrivetrain.whileHeld(new SlowDrive());
	    	
	    	Button vision = new JoystickButton(constants.stick, constants.vision);
	    	//vision.whenPressed(new ReadGrip());
	   // }
	    
	   /* Button shooterPivotUp = new JoystickButton(constants.shooter, constants.pivotUpButton);
	    shooterPivotUp.whenPressed(new ShooterPivotUp());
	    
	    Button shooterPivotDown = new JoystickButton(constants.shooter, constants.pivotDownButton);
	    shooterPivotDown.whenPressed(new ShooterPivotDown());
	
	    Button shooterPivotStop = new JoystickButton(constants.shooter, constants.pivotStop);
	    shooterPivotStop.whenPressed(new ShooterPivotStop());*/
	}
}
