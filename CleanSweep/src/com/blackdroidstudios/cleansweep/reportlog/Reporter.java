package com.blackdroidstudios.cleansweep.reportlog;

//Formatted output debugging and analysis 
//import com.blackdroidstudios.cleansweep.gui.Actor;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.surfacesensor.SurfaceSensor;

<<<<<<< HEAD
public class Reporter 
{
	public static void report()
	{
		//Tile currentTile = Sensor.getCurrent();
		//Clean Sweep current position
		//int xCoord = Actor.getInstance().getX();
		//int yCoord = Actor.getInstance().getY();
		
		/* NYI, need to talk with team about making static methods
		 * or other ways to pass info: 
		tileType floorTile = Floor.getTileType();
		floorType floorthing = Floor.getFloorType();
		int flrX = Floor.getX();
		int flrY = Floor.getY();
		int dirtLvl = Floor.getDirt();
		 */
=======
public class Reporter {
	
	private static Reporter instance = null;
	
	Reporter() {}
	
	int batteryCharge;
	int currentLocX;
	int currentLocY;
	int currentDirtLvl;
	floorType currentFlrType;
	
	public static Reporter getInstance(){
		if (instance == null){
			instance = new Reporter();
		}
		return instance;
	}
	
	public void setCurrentBattery(int charge)
	{
		this.batteryCharge = charge;
	}
	
	public void setCurrentLocX(int locX)
	{
		this.currentLocX = locX;
	}
	
	public void setCurrentLocY(int locY)
	{
		this.currentLocY = locY;
	}
	
	public void setCurrentDirtLvl(int dirtLvl)
	{
		this.currentDirtLvl = dirtLvl;
	}
	
	public void setCurrentFlrType(floorType flrType)
	{
		this.currentFlrType = flrType;
	}
	
	public static String report()
	{
		return ("Clean Sweep is currently at " + 
				Reporter.getInstance().currentLocX + "," + Reporter.getInstance().currentLocX +
				"\n with a battery level of: " + Reporter.getInstance().batteryCharge);
>>>>>>> cbc520a66b2ca41632f3abd8cee0083edd71a44c
	}
}