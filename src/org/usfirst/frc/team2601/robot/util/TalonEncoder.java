package org.usfirst.frc.team2601.robot.util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TalonEncoder {
	private CANTalon talon;
	
	public TalonEncoder(CANTalon talon){
		this.talon = talon;
	}
	public double getPosition(){
		return talon.getEncPosition();
	}
	public double getVelocity(){
		return talon.getEncVelocity();
	}
}

