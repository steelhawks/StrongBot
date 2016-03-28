package org.usfirst.frc.team2601.robot;

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
import org.usfirst.frc.team2601.robot.commands.shooter.LeftBallCorrection;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToFire;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToIntake;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToLowBar;
import org.usfirst.frc.team2601.robot.commands.shooter.MoveToStart;
import org.usfirst.frc.team2601.robot.commands.shooter.RightBallCorrection;
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
	    	Button shift = new JoystickButton(constants.stick, constants.shiftButton);
	    	shift.whenPressed(new Shift());
	    	
	    	//Button slowDrivetrain = new JoystickButton(constants.stick, constants.slowDrivetrainButton);
	    	//slowDrivetrain.whileHeld(new SlowDrive());
	    	
	    	Button gyroReset = new JoystickButton(constants.stick, constants.gyroResetButton);
	    	gyroReset.whenPressed(new GyroReset());
	    	
	    	//Button vision = new JoystickButton(constants.stick, constants.visionButton);
	    	//vision.whenPressed(new ReadCorners());
	    	
	    	Button stop = new JoystickButton(constants.stick, constants.stopMotorsButton);
	    	stop.whileHeld(new StopMotors());
	    	
	    	//Button align = new JoystickButton(constants.stick, constants.alignButton);
	    	//align.whenPressed(new AutoAlign());
	    	
	    	//Button driverContinuousWheels = new JoystickButton(constants.stick, constants.driverContinouousWheelsButton);
	   		//driverContinuousWheels.whenPressed(new ContinuousShoot());
	    	
	    	//Button driverPiston = new JoystickButton(constants.stick, constants.driverPistonButton);
	    	//driverPiston.whenPressed(new ContinuousPiston());
	    	
	    	//Button driverStopShoot= new JoystickButton(constants.stick, constants.driverStopShootButton);
	    	//driverStopShoot.whenPressed(new EStopShoot());
	    
	    //Operator Controls
	    	Button triggerShooter = new JoystickButton(constants.shooter, constants.triggerShooterButton);
	    	triggerShooter.whenPressed(new ContinuousPiston());
	    	
	    	Button piston = new JoystickButton(constants.shooter, constants.pistonButton);
	    	piston.whenPressed(new ShooterPiston());
	    
	    	Button continuousWheels = new JoystickButton(constants.shooter, constants.continuousWheelsButton);
	    	continuousWheels.whenPressed(new ContinuousShoot());
	    	
	    	Button stopShoot= new JoystickButton(constants.shooter, constants.stopShootButton);
	    	stopShoot.whenPressed(new EStopShoot());
	    	
	    	Button moveToStart = new JoystickButton(constants.shooter, constants.potStartButton);
	    	moveToStart.whenPressed(new MoveToStart());
	    	
	    	Button moveToIntake = new JoystickButton(constants.shooter, constants.potintakeButton);
	    	moveToIntake.whenPressed(new MoveToIntake());
	    	
	    	Button moveToLowBar = new JoystickButton(constants.shooter, constants.potlowBarButton);
	    	moveToLowBar.whenPressed(new MoveToLowBar());
	    	
	    	Button moveToFire = new JoystickButton(constants.shooter, constants.potFireButton);
	    	moveToFire.whenPressed(new MoveToFire());
	    	
	    	/*Button leftBallCorrection = new JoystickButton(constants.shooter, constants.ballCorrectionLeftButton);
	    	leftBallCorrection.whileHeld(new LeftBallCorrection());
	    	
	    	Button rightBallCorrection = new JoystickButton(constants.shooter, constants.ballCorrectionRightButton);
	    	rightBallCorrection.whileHeld(new RightBallCorrection());*/
	    	
	    	Button grapplingHook = new JoystickButton(constants.shooter, constants.grapplingHook);
	    	grapplingHook.whenPressed(new GrapplingHookPiston());
	}
}
