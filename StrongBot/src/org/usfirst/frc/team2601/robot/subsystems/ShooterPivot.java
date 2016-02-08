package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.ManualShooterPivot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 *
 */
public class ShooterPivot extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Constants constants = Constants.getInstance();
	
	CANTalon shooterPivotTalon = new CANTalon(constants.shooterPivotTalon);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualShooterPivot());
    }
    public void manualShooterPivot(Joystick stick){
    	double move = stick.getTwist();
    	shooterPivotTalon.set(move*constants.shooterPivotSpeed);
    }
    public void shooterPivotUp(){
    	shooterPivotTalon.set(constants.shooterPivotSpeed*constants.shooterPivotUpMultiplier); 
    }
    public void shooterPivotDown(){
    	shooterPivotTalon.set(constants.shooterPivotSpeed*constants.shooterPivotDownMultiplier);
    }
    public void shooterPivotStop(){
    	shooterPivotTalon.set(0);
    }
}