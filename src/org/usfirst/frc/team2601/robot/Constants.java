package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.util.TalonEncoder;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Constants {
    
	private static Constants instance = null;
	
	public static Constants getInstance(){
		if (instance == null){
			instance = new Constants();
		}
		return instance;
	}
	
	public enum System_Type {Alpha, Beta};
	public System_Type system = System_Type.Beta;
	
	public enum Driver_Type {Arcade, Tank, Gamepad};
	public Driver_Type driver = Driver_Type.Arcade;
	
	public enum System_Mode {All, DriveOnly, ShootOnly};  // pfw - added to toggle between shooting & driving for testing
	public System_Mode mode = System_Mode.ShootOnly;
	
	public final boolean PNEUMATICS_ON = true;
		
	//Talons
	public int frontLeftTalon;
    public int backLeftTalon;
    public int frontRightTalon;
    public int backRightTalon;
    
    public int topRollerTalon;
    public int bottomRollerTalon;    
    public int shooterPivotTalon;
    
    public int armUpTalon;
    public int armExtendTalon;
    public int armRetractTalon;
    
    //Encoders
    public int leftEncoderPortI = 0;
	public int leftEncoderPortII = 1;
	public int rightEncoderPortI = 2;
	public int rightEncoderPortII = 3;
	
	//Ultrasonics
	public int ultrasonicInput;
	public int ultrasonicOutput;
	public double ultrasonicValue;
	
	//Gyros
	public int gyroAnalogPin;
	public double gyroAngle = 0.0;
	public double gyroRate = 0.0;
	
	//Potentiometers
	public int shooterPivotPotAnalogPin = 1; 
	
    //Solenoids
    public int shooterSolenoidOn = 4;
    public int shooterSolenoidOff = 5;
    public int leftSolenoidOn = 0;
    public int leftSolenoidOff = 1;
    public int rightSolenoidOn = 2;
    public int rightSolenoidOff = 3;
    
    //Rollers
    public int topRollerMultiplier = -1;
    public int bottomRollerMultiplier = 1;
    
    public int rollerSpeed = 1;
    public double intakeSpeed = 1;
    
    
    public double scaleSpeed = 1.0;
    public double armUpSpeed = 0.5;
    //public static int scaleMultiplier = 1;
    public int shooterPivotUpMultiplier = 1;
    public int shooterPivotDownMultiplier = -1;
    public double shooterPivotSpeed = 1.0;
    public double slowDrivetrainSpeed = 0.5;
    
  //autonomous commands and multipliers
  	//drivetrain
  	public double autonForward = 1.0;
  	public double autonSlowForward = 0.5;
  	public double autonBackward = -1.0;
  	public double autonSlowBackward = -0.5;
  	public double autonTurnSpeed = 1.0;
  	public double autonSlowTurnSpeed = 0.5;

  	public double leftDrivetrainMultiplier = 1;
  	public double rightDrivetrainMultiplier = -1;
  	
  	//shooter
  	public double autonShootSpeed = 1.0;
  	public double autonIntakeSpeed = -0.5;
  		
  	//shooter pivot
  	public double autonPivotSpeed = 1.0;
  	public double autonSlowPivotSpeed = 0.5;
  	
 
    //Joysticks
    public Joystick stick;
	public Joystick shooter;
    public int joystickPort = 0;
    public int secondJoystickPort = 1;
    
    //Driver Buttons
    public int shiftButton = 1;
    public int slowDrivetrainButton = 2;
    public int vision = 4;
    
	//Operator Buttons
	public int continuousWheelsButton = 2;
	public int pistonButton = 3;
	public int switchScaleMotorsButton = 4;
	public int stopShootButton = 5;
	public int triggerShooterButton = 1; 
	/*public int pivotUpButton = 9;
	public int pivotDownButton = 10;
	public int pivotStop = 5;*/
	
	//variables that shouldn't be in constants
	//public boolean scale;//used to switch between the two motors
	public boolean roll = false;//used to see if the rollers are continuous or not
	public boolean shot = false;//used to see if piston is extended or nah
	
	public final boolean logging = false;
	
	//PID Values
	public double kP = 0.0;
	public double kI = 0.0;
	public double kD = 0.0;
	public double kF = 0.0;
	public double PIDtolerance = 20.0;
	
	public final double distancePerPulse = 0.5;
	
    private Constants(){
	   if(system == System_Type.Beta){
		    frontLeftTalon = 1;
		    backLeftTalon = 2;
		    
		    frontRightTalon = 3;
		    backRightTalon = 4;
		    
		    topRollerTalon = 5;
		    bottomRollerTalon = 6;
		    shooterPivotTalon = 7;
		    
		    armUpTalon = 1;//8
		    armExtendTalon = 0;
		    armRetractTalon = 2;//12
		    
		    ultrasonicInput = 4;
		    ultrasonicOutput = 5;
		    
		    gyroAnalogPin = 0;
		    
	   }
	   else if(system == System_Type.Alpha){
		   frontLeftTalon = 10;
		    backLeftTalon = 20;
		    
		    frontRightTalon = 3;
		    backRightTalon = 4;
		    
		    topRollerTalon = 5;
		    bottomRollerTalon = 6;
		    shooterPivotTalon = 7;
		    
		    armUpTalon = 1;//8
		    armExtendTalon = 0;
		    armRetractTalon = 2;//12
		    
		    ultrasonicInput = 4;
		    ultrasonicOutput = 5;
		    
		    gyroAnalogPin = 0;
			   }
    }
}