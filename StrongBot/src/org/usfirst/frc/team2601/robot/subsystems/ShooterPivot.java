package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.shooter.ManualShooterPivot;
import org.usfirst.frc.team2601.robot.util.TalonEncoder;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 
 */
public class ShooterPivot extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Constants constants = Constants.getInstance();
	
	CANTalon shooterPivotTalon = new CANTalon(constants.shooterPivotTalon);
	
	TalonEncoder shooterPivotEncoder = new TalonEncoder(shooterPivotTalon);
	
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualShooterPivot());
    }
    public void manualShooterPivot(Joystick stick){
    	double move = stick.getX();
    	shooterPivotTalon.set(move*constants.shooterPivotSpeed);
    	
    	SmartDashboard.putNumber("ShooterPivotEncoderPosition",shooterPivotEncoder.getPosition());
    	SmartDashboard.putNumber("ShooterPivotEncoderVelocity",shooterPivotEncoder.getVelocity());
    }
    public void autonShooterPivotUp(){
    	shooterPivotTalon.set(constants.autonPivotSpeed*constants.shooterPivotUpMultiplier); 
    }
    public void autonShooterPivotDown(){
    	shooterPivotTalon.set(constants.autonPivotSpeed*constants.shooterPivotDownMultiplier);
    }
    public void autonSlowShooterPivotUp(){
    	shooterPivotTalon.set(constants.autonSlowPivotSpeed*constants.shooterPivotUpMultiplier);
    }
    public void autonSlowShooterPivotDown(){
    	shooterPivotTalon.set(constants.autonSlowPivotSpeed*constants.shooterPivotDownMultiplier);
    }
    public void shooterPivotStop(){
    	shooterPivotTalon.set(0);
    }
}