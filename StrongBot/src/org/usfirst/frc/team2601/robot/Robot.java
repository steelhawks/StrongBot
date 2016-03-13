
package org.usfirst.frc.team2601.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;

import java.io.IOException;

import org.usfirst.frc.team2601.robot.commands.Drive;
import org.usfirst.frc.team2601.robot.commands.autonCommands.AutonTest;
import org.usfirst.frc.team2601.robot.commands.autonCommands.CrossLowBar;
import org.usfirst.frc.team2601.robot.commands.autonCommands.CrossLowBarShootHighGoal;
import org.usfirst.frc.team2601.robot.commands.autonCommands.CrossLowBarShootTwoHighGoals;
import org.usfirst.frc.team2601.robot.commands.autonCommands.DoNothing;
import org.usfirst.frc.team2601.robot.commands.autonCommands.GyroForward;
import org.usfirst.frc.team2601.robot.commands.autonCommands.VisionAutonTest;
import org.usfirst.frc.team2601.robot.commands.autonCommands.five.CrossChevalDeFriseShootHighGoalFive;
import org.usfirst.frc.team2601.robot.commands.autonCommands.five.CrossMoatShootHighGoalFive;
import org.usfirst.frc.team2601.robot.commands.autonCommands.five.CrossRampartsShootHighGoalFive;
import org.usfirst.frc.team2601.robot.commands.autonCommands.five.CrossRockWallShootHighGoalFive;
import org.usfirst.frc.team2601.robot.commands.autonCommands.five.CrossRoughTerrainShootHighGoalFive;
import org.usfirst.frc.team2601.robot.commands.autonCommands.four.CrossChevalDeFriseShootHighGoalFour;
import org.usfirst.frc.team2601.robot.commands.autonCommands.four.CrossMoatShootHighGoalFour;
import org.usfirst.frc.team2601.robot.commands.autonCommands.four.CrossRampartsShootHighGoalFour;
import org.usfirst.frc.team2601.robot.commands.autonCommands.four.CrossRockWallShootHighGoalFour;
import org.usfirst.frc.team2601.robot.commands.autonCommands.four.CrossRoughTerrainShootHighGoalFour;
import org.usfirst.frc.team2601.robot.commands.autonCommands.three.CrossChevalDeFriseShootHighGoalThree;
import org.usfirst.frc.team2601.robot.commands.autonCommands.three.CrossMoatShootHighGoalThree;
import org.usfirst.frc.team2601.robot.commands.autonCommands.three.CrossRampartsShootHighGoalThree;
import org.usfirst.frc.team2601.robot.commands.autonCommands.three.CrossRockWallShootHighGoalThree;
import org.usfirst.frc.team2601.robot.commands.autonCommands.three.CrossRoughTerrainShootHighGoalThree;
import org.usfirst.frc.team2601.robot.commands.autonCommands.two.CrossChevalDeFriseShootHighGoalTwo;
import org.usfirst.frc.team2601.robot.commands.autonCommands.two.CrossMoatShootHighGoalTwo;
import org.usfirst.frc.team2601.robot.commands.autonCommands.two.CrossRampartsShootHighGoalTwo;
import org.usfirst.frc.team2601.robot.commands.autonCommands.two.CrossRockWallShootHighGoalTwo;
import org.usfirst.frc.team2601.robot.commands.autonCommands.two.CrossRoughTerrainShootHighGoalTwo;
import org.usfirst.frc.team2601.robot.subsystems.Camera;
import org.usfirst.frc.team2601.robot.subsystems.CombinedShooter;
import org.usfirst.frc.team2601.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2601.robot.subsystems.Scaler;
import org.usfirst.frc.team2601.robot.subsystems.Shooter;
import org.usfirst.frc.team2601.robot.subsystems.ShooterPivot;
import org.usfirst.frc.team2601.robot.subsystems.ShooterRoller;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain = new Drivetrain();
	//public static Shooter shooter = new Shooter();
	//public static ShooterRoller roller = new ShooterRoller();
	//public static ShooterPivot shooterPivot = new ShooterPivot();
	//public static Scaler scaler = new Scaler();
    public static Camera camera = new Camera();
	public static OI oi;
	public static CombinedShooter combinedshooter  = new CombinedShooter();

    Command autonomousCommand;
    SendableChooser chooser;
    
    Compressor compressor;
    
    Constants constants = Constants.getInstance();
    
    BuiltInAccelerometer accel;
    double accelx = 0.0;
    double accely = 0.0;
    
	//AnalogPotentiometer pot = new AnalogPotentiometer(constants.shooterPivotPotAnalogPin,360,60);
    
    CameraServer server = CameraServer.getInstance();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	    	oi = new OI();
	    	
	    	server.setQuality(90);
	    	server.startAutomaticCapture("cam0");
	    	
	    	if(constants.PNEUMATICS_ON){
	        	compressor = new Compressor();
	        	compressor.start();
	    	}
	    	System.out.println("encoder");
	    	
	        accel = new BuiltInAccelerometer();

	        chooser = new SendableChooser();
	        
	        //chooser.addDefault("CrossLowBarShootTwoHighGoals", new CrossLowBarShootTwoHighGoals());
	        //chooser.addObject("AutonTest", new AutonTest());
	        //chooser.addObject("Gyro Forward", new GyroForward());
	        //chooser.addObject("LowBarCross", new CrossLowBar());
	        chooser.addDefault("CrossLowBarShootHighGoal", new CrossLowBarShootHighGoal());
	        //chooser.addObject("AutonVision", new VisionAutonTest());
	        chooser.addObject("NULL", new DoNothing());
	        //chooser.addObject("CrossChevalDeFriseShootHighGoal5", new CrossChevalDeFriseShootHighGoalFive());
	        chooser.addObject("CrossMoatShootHighGoal5", new CrossMoatShootHighGoalFive());
	        chooser.addObject("CrossRampartsShootHighGoal5", new CrossRampartsShootHighGoalFive());
	        chooser.addObject("CrossRockWallShootHighGoal5", new CrossRockWallShootHighGoalFive());
	        chooser.addObject("CrossRoughTerrainShootHighGoal5", new CrossRoughTerrainShootHighGoalFive());
	        //chooser.addObject("NULL", new DoNothing());
	        //chooser.addObject("CrossChevalDeFriseShootHighGoal4", new CrossChevalDeFriseShootHighGoalFour());
	        //chooser.addObject("CrossMoatShootHighGoal4", new CrossMoatShootHighGoalFour());
	        //chooser.addObject("CrossRampartsShootHighGoal4", new CrossRampartsShootHighGoalFour());
	        //chooser.addObject("CrossRockWallShootHighGoal4", new CrossRockWallShootHighGoalFour());
	        //chooser.addObject("CrossRoughTerrainShootHighGoal4", new CrossRoughTerrainShootHighGoalFour());
	        //chooser.addObject("NULL", new DoNothing());
	        //chooser.addObject("CrossChevalDeFriseShootHighGoal3", new CrossChevalDeFriseShootHighGoalThree());
	        //chooser.addObject("CrossMoatShootHighGoal3", new CrossMoatShootHighGoalThree());
	        //chooser.addObject("CrossRampartsShootHighGoal3", new CrossRampartsShootHighGoalThree());
	        //chooser.addObject("CrossRockWallShootHighGoal3", new CrossRockWallShootHighGoalThree());
	        //chooser.addObject("CrossRoughTerrainShootHighGoal3", new CrossRoughTerrainShootHighGoalThree());
	        //chooser.addObject("NULL", new DoNothing());
	        //chooser.addObject("CrossChevalDeFriseShootHighGoal2", new CrossChevalDeFriseShootHighGoalTwo());
	        //chooser.addObject("CrossMoatShootHighGoal2", new CrossMoatShootHighGoalTwo());
	        //chooser.addObject("CrossRampartsShootHighGoal2", new CrossRampartsShootHighGoalTwo());
	        //chooser.addObject("CrossRockWallShootHighGoal2", new CrossRockWallShootHighGoalTwo());
	        //chooser.addObject("CrossRoughTerrainShootHighGoal2", new CrossRoughTerrainShootHighGoalTwo());
	        SmartDashboard.putData("Auto mode", chooser);
	   
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	Robot.drivetrain.gyro.reset();		
    	
    	autonomousCommand = (Command) chooser.getSelected();
    	autonomousCommand.start();
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case ";' Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
    }

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	if (autonomousCommand != null) autonomousCommand.cancel();
    	Robot.drivetrain.gyro.reset();		

	}

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("RoboRioAccelx", accel.getX());
        SmartDashboard.putNumber("RoboRioAccely", accel.getY());
        SmartDashboard.putNumber("RoboRioAccelz", accel.getZ());       
                                                                               
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
