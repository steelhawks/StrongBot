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
	
	public enum Claw_Type {Circle, Square};
	public Claw_Type claw = Claw_Type.Circle;
	
	public enum Shoot_Auton {Yes, No};
	public Shoot_Auton shootOrNot = Shoot_Auton.Yes;
	
	public final boolean PNEUMATICS_ON = true;
		
	//Talons
	public int frontLeftTalon;
    public int backLeftTalon;
    public int frontRightTalon;
    public int backRightTalon;
    
    public int leftRollerTalon;
    public int rightRollerTalon;    
    public int shooterPivotTalon;
    
    public int armUpTalon;
    public int armExtendTalon;
    public int armRetractTalon;
    
    public int winchTalon;
    
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
	
	//Potentiometers
	public int shooterPivotPotAnalogPin = 0; 
	
    //Drivetrain Solenoids
    public int leftSolenoidOn = 0;
    public int leftSolenoidOff = 1;
    public int rightSolenoidOn = 2;
    public int rightSolenoidOff = 3;
    //Shooter Solenoids
    public int leftShooterSolenoidOn = 4;
    public int leftShooterSolenoidOff = 5;
    public int rightShooterSolenoidOn = 6;
    public int rightShooterSolenoidOff = 7;
    //Scaler Solenoids
    public int scalerSolenoidOn = 8;
    public int scalerSolenoidOff = 9;
    
    //Rollers
    public int topRollerMultiplier = -1;
    public int bottomRollerMultiplier = 1;
    
    public int rollerSpeed = 1;
    public double intakeSpeed = 0.5;
    public double ballCorrectionSpeed = 0.2;
    
    public double scaleSpeed = 1.0;
    public double armUpSpeed = 0.5;
    //public int scaleMultiplier = 1;
    public int shooterPivotUpMultiplier = -1;
    public int shooterPivotDownMultiplier = 1;
    public double twistMultiplier = 0.75;
    public double shooterPivotSpeed = 1.0;
    public double buttonPivotSpeed = 1.0;
    public double slowDrivetrainSpeed = 0.75;
    
  //autonomous commands and multipliers
  	//drivetrain
  	public double autonForward = -1.0;
  	public double autonSlowForward = -0.5;
  	public double autonBackward = 1.0;
  	public double autonSlowBackward = 0.5;
  	public double autonTurnSpeed = 1.0;
  	public double autonSlowTurnSpeed = 0.5;
  	public double autonLowBarSpeed = -0.75;
  	//public double autonGyroSpeed = 0.5;
  	
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
    public int visionButton = 10;
    public int gyroResetButton = 3;
    public int stopMotorsButton = 7;
    public int alignButton = 12;
    public int driverContinouousWheelsButton = 11;
    public int driverPistonButton = 12;
    public int driverStopShootButton = 9;
    
	//Operator Buttons
	public int continuousWheelsButton = 3;	//2 on the old joystick
	public int triggerShooterButton = 1;
	public int pistonButton = 5;
	public int stopShootButton = 6;
	public int potStartButton = 8;
	public int potFireButton = 7;
	public int potlowBarButton = 9;
	public int potintakeButton = 11;
	//public int switchScaleMotorsButton = 4;
	//public int grapplingHook = 6;
	public int ballCorrectionLeftButton = 10;
	public int ballCorrectionRightButton = 12;
	
	//variables that shouldn't be in constants
	//public boolean scale;//used to switch between the two motors
	public boolean roll = false;//rollers are continuous or not
	public boolean shot = false;//piston is extended or not
	public boolean grapple = false;//scaler shooter is shot or not
	
	public final boolean logging = false;

	//PID Values
	public double kP = 0.0;
	public double kI = 0.0;
	public double kD = 0.0;
	
	public double kF = 0.0;
	public double PIDtolerance = 20.0;
	
	public final double distancePerPulse = 0.5;
	
	//Potentiometer Shooter Pivot Values
	public double minPot = 235;	//highest
	public double maxPot = minPot + 150;	//lowest/2
	public double startPot = minPot;
	public double shootPot = minPot + 20;//290 - 
	public double lowBarPot = minPot + 140;
	public double intakePot = minPot + 150;
	public double potTolerance = 5;
	
	//Servo Pivot Values
	public double maxServoAngle = 140;
	public double minServoAngle = 90;
	
	//autonUltrasonicValue
	//public double autonUltrasonicDistance = 100;
	public double autonUltrasonicTolerance = 10;
	
	//autonGyroAngle
	public double autonGyroCorrection = 0;
	public double autonGyroAngleLeftLowGoal = 47;
	public double autonGyroAngleLeftHighGoal = 52;
	public double autonGyroAngleRightLowGoal = -30;
	public double autonGyroAngleRightLowBar = -36;
	public double autonGyroTolerance = 0.5;
	
    private Constants(){
	   if(system == System_Type.Beta){
		    frontLeftTalon = 1;
		    backLeftTalon = 2;
		    
		    frontRightTalon = 3;
		    backRightTalon = 4;
		    
		    leftRollerTalon = 5;//left
		    rightRollerTalon = 6;//right
		    shooterPivotTalon = 0;//7
		    
		    winchTalon = 8;
		    
		 //   armUpTalon = 1;//8
		 //   armExtendTalon = 0;
		 //   armRetractTalon = 2;//12
		    
		    ultrasonicInput = 4;
		    ultrasonicOutput = 5;
		    
		    gyroAnalogPin = 1;
		    
	   }
	   else if(system == System_Type.Alpha){
		   frontLeftTalon = 1;
		    backLeftTalon = 2;
		    
		    frontRightTalon = 3;
		    backRightTalon = 4;
		    
		    leftRollerTalon = 5;
		    rightRollerTalon = 6;
		    shooterPivotTalon = 0;
		    
		   // armUpTalon = 8;//8
		    //armExtendTalon = 0;
		    //armRetractTalon = 12;//12
		    
		    ultrasonicInput = 4;
		    ultrasonicOutput = 5;
		    
		    gyroAnalogPin = 0;
			   }
    }
}