package org.usfirst.frc.team2601.robot.subsystems;

import java.util.List;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import java.util.ArrayList;
import java.util.Collections;

import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
import edu.wpi.first.wpilibj.util.SortedVector.Comparator;

/**
 *
 */
public class Camera extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Constants constants = Constants.getInstance();
	
	public int x = 0;
	public int y = 0;
	int s;
	public boolean aligned = false;


	public ArrayList<Point> leftCoords = new ArrayList<Point>();
	public ArrayList<Point> rightCoords = new ArrayList<Point>();	
	public Point UL = new Point();
	public Point LL = new Point();
	public Point UR = new Point();
	public Point LR = new Point();
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    @SuppressWarnings("deprecation")
	public void read(){
    	NetworkTable server = NetworkTable.getTable("/");
		//ArrayList<Integer> xValues = new ArrayList<Integer>();
		//ArrayList<Integer> yValues = new ArrayList<Integer>();
		//final ArrayList<Number> corner = new ArrayList<Number>()
    	ArrayList<Point> xyCoord = new ArrayList<Point>();	
    	ArrayList<Point> corners = new ArrayList<Point>();
    	
    	Double[] smth = new Double[10];
		smth[0] = 1.0;
		Double[] values = new Double[20];		
		try
    	{
    		//server.retrieveValue("LINE_CORNER", corner);
    		values = server.getNumberArray("LINE_CORNER", smth);
    		System.out.println("got values");
    	}
    	catch (TableKeyNotDefinedException exp)
    	{
    		System.out.println("can't retrieve values");
    	}
		if(values.length>0){
    		for(int i=0;i<values.length-1;i+=2){
   				xyCoord.add(new Point(values[i].intValue(),values[i+1].intValue()));
    			System.out.println("xyCoord" + (i/2) + ": " + xyCoord.get(i/2).x + ", " + xyCoord.get(i/2).y);
    		}
    		for(int i=0;i<xyCoord.size();i++){
    			s = 0;
    			if(corners.size() == 0){
    				corners.add(xyCoord.get(i));
        			System.out.print("xyCoord" + i +" added");
        			System.out.println("");
    			}else if(!corners.contains(xyCoord.get(i))){
    				System.out.println("xyCoord" + i + " not in corners");
    				while(s<corners.size()){
   						if(Math.abs((((xyCoord.get(i)).x)-(corners.get(s)).x))<5 &&
   							Math.abs((((xyCoord.get(i)).y)-(corners.get(s)).y))<5){
   							System.out.println("xyCoord" + i + " is close to corner " + s);
   							s = corners.size();
   						}
    					s++;
 					}
    				if(s == corners.size()){
    					corners.add(xyCoord.get(i));
            			System.out.print("xyCoord" + i +" added to corners");
    				}
   				}
   			}
   			for(int i=0;i<corners.size();i++){
   				System.out.println("Corner" + i + " is " + corners.get(i).x + ", " + corners.get(i).y);
   			}
   			if(corners.size() == 4){
	   			//left
   				if((((corners.get(0)).x) < (corners.get(1)).x) && ((corners.get(0)).x < (corners.get(2)).x) || (((corners.get(0)).x) < (corners.get(2)).x) && ((corners.get(0)).x < (corners.get(3)).x) || (((corners.get(0)).x) < (corners.get(1)).x) && ((corners.get(0)).x < (corners.get(3)).x)){
	   				leftCoords.add(corners.get(0));
	   				System.out.println("Corner 0 assigned to Left");
				}
	   			if((((corners.get(1)).x) < (corners.get(0)).x) && ((corners.get(1)).x < (corners.get(2)).x) || (((corners.get(1)).x) < (corners.get(2)).x) && ((corners.get(1)).x < (corners.get(3)).x) || (((corners.get(1)).x) < (corners.get(0)).x) && ((corners.get(1)).x < (corners.get(3)).x)){
	   				leftCoords.add(corners.get(1));
	   				System.out.println("Corner 1 assigned to Left");
	   			}
	  			if((((corners.get(2)).x) < (corners.get(1)).x) && ((corners.get(2)).x < (corners.get(3)).x) || (((corners.get(2)).x) < (corners.get(3)).x) && ((corners.get(2)).x < (corners.get(3)).x) || (((corners.get(2)).x) < (corners.get(0)).x) && ((corners.get(2)).x < (corners.get(3)).x)){
	   				leftCoords.add(corners.get(2));
	   				System.out.println("Corner 2 assigned to Left");
				}
	   			if((((corners.get(3)).x) < (corners.get(0)).x) && ((corners.get(3)).x < (corners.get(2)).x) || (((corners.get(3)).x) < (corners.get(1)).x) && ((corners.get(3)).x < (corners.get(2)).x) || (((corners.get(3)).x) < (corners.get(0)).x) && ((corners.get(3)).x < (corners.get(1)).x)){
	   				leftCoords.add(corners.get(3));
	   				System.out.println("Corner 3 assigned to Left");
	   			}
	   			//right
	   			if((((corners.get(0)).x) > (corners.get(1)).x) && ((corners.get(0)).x > (corners.get(2)).x) || (((corners.get(0)).x) > (corners.get(2)).x) && ((corners.get(0)).x > (corners.get(3)).x) || (((corners.get(0)).x) > (corners.get(1)).x) && ((corners.get(0)).x > (corners.get(3)).x)){
	   				rightCoords.add(corners.get(0));
	   				System.out.println("Corner 0 assigned to Right");
	   			}
	  			if((((corners.get(1)).x) > (corners.get(0)).x) && ((corners.get(1)).x > (corners.get(2)).x) || (((corners.get(1)).x) > (corners.get(2)).x) && ((corners.get(1)).x > (corners.get(3)).x) || (((corners.get(1)).x) > (corners.get(0)).x) && ((corners.get(1)).x > (corners.get(3)).x)){
	   				rightCoords.add(corners.get(1));
	  				System.out.println("Corner 1 assigned to Right");
				}
	   			if((((corners.get(2)).x) > (corners.get(1)).x) && ((corners.get(2)).x > (corners.get(3)).x) || (((corners.get(2)).x) > (corners.get(0)).x) && ((corners.get(2)).x > (corners.get(3)).x) || (((corners.get(2)).x) > (corners.get(1)).x) && ((corners.get(2)).x > (corners.get(0)).x)){
	   				rightCoords.add(corners.get(2));
	   				System.out.println("Corner 2 assigned to Right");
				}
	   			if((((corners.get(3)).x) > (corners.get(0)).x) && ((corners.get(3)).x > (corners.get(2)).x) || (((corners.get(3)).x) > (corners.get(1)).x) && ((corners.get(3)).x > (corners.get(2)).x) || (((corners.get(3)).x) > (corners.get(0)).x) && ((corners.get(3)).x > (corners.get(1)).x)){
	   				rightCoords.add(corners.get(3));
	   				System.out.println("Corner 3 assigned to Right");
				}   				
	   			if(leftCoords.size() == 2){
	   				if((leftCoords.get(0)).y > (leftCoords.get(1)).y){
	   					LL = leftCoords.get(0);
	   					UL = leftCoords.get(1);
	   					System.out.println("LowerLeft = " + LL.x + ", " + LL.y);
	   					System.out.println("UpperLeft = " + UL.x + ", " + UL.y);
	   				}
	   				if((leftCoords.get(0)).y < (leftCoords.get(1)).y){
	   					LL = leftCoords.get(1);
	   					UL = leftCoords.get(0);
	   					System.out.println("LowerLeft = " + LL.x + ", " + LL.y);
						System.out.println("UpperLeft = " + UL.x + ", " + UL.y);
	   				}
	   			}
	   			if(rightCoords.size() == 2){
	   				if((rightCoords.get(0)).y > (rightCoords.get(1)).y){
	   					LR = rightCoords.get(0);
	   					UR = rightCoords.get(1);
						System.out.println("LowerRight = " + LR.x + ", " + LR.y);
	   					System.out.println("UpperRight = " + UR.x + ", " + UR.y);
	   				}
	   				if((rightCoords.get(0)).y < (rightCoords.get(1)).y){
	   					LR = rightCoords.get(1);
	   					UR = rightCoords.get(0);
	   					System.out.println("LowerRight = " + LR.x + ", " + LR.y);
	   					System.out.println("UpperRight = " + UR.x + ", " + UR.y);
	   				}
	   			}
   			}
		}
    }
    public void align(){
		//slope values
			double a = LR.y - LL.y;
			double b = LR.x - LL.x;
			double slope = a/b;
			//distance and midpoint values
			int lowerX = (LL.x + LR.x)/2;
			double GoalCenterX = 160;
			double GoalCenterXTolerance = 15;
			boolean moveLeftMid = false;
			boolean moveRightMid = false;
			
			System.out.println(slope);
			if(-1 > slope && lowerX > GoalCenterX + GoalCenterXTolerance){
				System.out.println("Turn Left");
				
				moveLeftMid = true;
				moveRightMid = false;
				aligned = false;
				SmartDashboard.putBoolean("Turn Left", moveLeftMid);
				SmartDashboard.putBoolean("Turn Right", moveRightMid);
				SmartDashboard.putBoolean("Aligned", aligned);
				
				Robot.drivetrain.frontLeftMotor.set(constants.autonSlowBackward*constants.leftDrivetrainMultiplier);
				Robot.drivetrain.backLeftMotor.set(constants.autonSlowBackward*constants.leftDrivetrainMultiplier);
				Robot.drivetrain.frontRightMotor.set(constants.autonSlowForward*constants.rightDrivetrainMultiplier);
				Robot.drivetrain.backRightMotor.set(constants.autonSlowForward*constants.rightDrivetrainMultiplier);
			}
			if(slope > 0 && lowerX < GoalCenterX - GoalCenterXTolerance){
				System.out.println("Turn Right");
				moveLeftMid = false;
				moveRightMid = true;
				aligned = false;
				SmartDashboard.putBoolean("Turn Left", moveLeftMid);	
				SmartDashboard.putBoolean("Turn Right", moveRightMid);
				SmartDashboard.putBoolean("Aligned", aligned);
				
				Robot.drivetrain.frontLeftMotor.set(constants.autonSlowForward*constants.leftDrivetrainMultiplier);
				Robot.drivetrain.backLeftMotor.set(constants.autonSlowForward*constants.leftDrivetrainMultiplier);
				Robot.drivetrain.frontRightMotor.set(constants.autonSlowBackward*constants.rightDrivetrainMultiplier);
				Robot.drivetrain.backRightMotor.set(constants.autonSlowBackward*constants.rightDrivetrainMultiplier);
			}
			if(slope < 0 && slope > -1 && lowerX <= GoalCenterX + GoalCenterXTolerance && lowerX >= GoalCenterX - GoalCenterXTolerance){
				System.out.println("FIRE");
				aligned = true;
				moveLeftMid = false;
				moveRightMid = false;
				SmartDashboard.putBoolean("Turn Left", moveLeftMid);
				SmartDashboard.putBoolean("Turn Right", moveRightMid);
				SmartDashboard.putBoolean("Aligned", aligned);
			
				Robot.drivetrain.frontLeftMotor.set(0);
				Robot.drivetrain.backLeftMotor.set(0);
				Robot.drivetrain.frontRightMotor.set(0);
				Robot.drivetrain.backRightMotor.set(0);
			}
    	}
	}