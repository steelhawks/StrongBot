package org.usfirst.frc.team2601.robot;

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
    
    public int armUpTalon;
    public int scaleTalon;
    
    public int shooterPivotTalon;
    
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
    public double intakeSpeed = 0.5;
    public int stopRollerSpeed = 0;
    
    public int scaleSpeed = 1;
   // public static int scaleMultiplier = 1;
    public int shooterPivotUpMultiplier = 1;
    public int shooterPivotDownMultiplier = -1;
    public double shooterPivotSpeed = 1.0;
    
    //Joysticks
    public Joystick stick;
	public Joystick shooter;
    public int joystickPort = 0;
    public int secondJoystickPort = 1;
    
    //Driver Buttons
    public int shiftButton = 1;
    
	//Operator Buttons
	public int triggerShooterButton = 1;
	public int continuousWheelsButton = 2;
	public int switchScaleMotorsButton = 4;
    
	/*public int pivotUpButton = 9;
	public int pivotDownButton = 10;
	public int pivotStop = 5;*/
	
	public boolean scale;//used to switch between the two motors
	public boolean roll = false;//used to see if the rollers are continuous or not
	public boolean shot = false;//used to see if piston is extended or nah
	
	public final boolean logging = true;
	
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
		    
		    armUpTalon = 8;
		    scaleTalon = 0;	
		    
		    shooterPivotTalon = 7;
	   }
	   else if(system == System_Type.Alpha){
		    frontLeftTalon = 8;
		    backLeftTalon = 10;
		    
		    frontRightTalon = 7;
		    backRightTalon = 11;
		    
		    topRollerTalon = 5;
		    bottomRollerTalon = 4; 
		
		    armUpTalon = 6;
		    scaleTalon = 0;
		    
		    shooterPivotTalon = 18;
	   }
    }
}