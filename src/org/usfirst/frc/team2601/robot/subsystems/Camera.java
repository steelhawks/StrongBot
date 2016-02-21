package org.usfirst.frc.team2601.robot.subsystems;

import java.io.Console;
import java.lang.reflect.Array;
import java.nio.IntBuffer;
import java.util.ArrayList;

import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 *
 */
public class Camera extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public int x = 0;
	public int y = 0;
	public int s = 0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    @SuppressWarnings("deprecation")
	public void read(){
    	NetworkTable server = NetworkTable.getTable("/");
		//ArrayList<Integer> xValues = new ArrayList<Integer>();
		//ArrayList<Integer> yValues = new ArrayList<Integer>();
		ArrayList<Point> xyCoord = new ArrayList<Point>();	
		ArrayList<Point> corners = new ArrayList<Point>();
		final ArrayList<Number> corner = new ArrayList<Number>();
		ArrayList<Point> orderedList = new ArrayList<Point>(); 
		Double[] smth = new Double[10];
		smth[0] = 1.0;
		Double[] values = new Double[20];		
		try
    	{
    		//server.retrieveValue("LINE_CORNER", corner);
    		values = server.getNumberArray("LINE_CORNER", smth);
    	}
    	catch (TableKeyNotDefinedException exp)
    	{
    		System.out.println("not working");
    	}
		if(values.length>0){
    		for(int i=0;i<values.length-1;i+=2){
   				xyCoord.add(new Point(values[i].intValue(),values[i+1].intValue()));
    			System.out.println("xyCoord" + (i/2) + ": " + xyCoord.get(i/2).x + ", " + xyCoord.get(i/2).y);
    		}
    		for(int i=0;i<xyCoord.size()-1;i++){
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
            			System.out.print("xyCoord" + i +" added");
    				}
   				}
   			}
   			for(int i=0;i<corners.size();i++){
   				System.out.println("Corner" + i + " is " + corners.get(i).x + ", " + corners.get(i).y);
   				if((corners.get(0)).y > (corners.get(1)).y && (corners.get(0)).y > (corners.get(2)).y && (corners.get(0)).y > (corners.get(3)).y){
   					orderedList.add(corners.get(0));
   				}
   				if((corners.get(1)).y > (corners.get(0)).y && (corners.get(1)).y > (corners.get(2)).y && (corners.get(1)).y > (corners.get(3)).y){
   					orderedList.add(corners.get(1));
   				}
   				if((corners.get(2)).y > (corners.get(0)).y && (corners.get(2)).y > (corners.get(1)).y && (corners.get(2)).y > (corners.get(3)).y){
   					orderedList.add(corners.get(2));
   				}
   				if((corners.get(3)).y > (corners.get(0)).y && (corners.get(3)).y > (corners.get(1)).y && (corners.get(3)).y > (corners.get(2)).y){
   					orderedList.add(corners.get(3));
   				}
   				
   			}
   		}
    }
}


