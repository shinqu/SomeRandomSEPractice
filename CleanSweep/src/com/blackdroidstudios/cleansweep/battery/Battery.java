package com.blackdroidstudios.cleansweep.battery;

import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;

public class Battery 
{	
	private static long batteryCharge = 100;
	private long batteryLoss = 0;
	private floorType myCurrentFloor;
	
	
	/*switch(myCurrentFloor)
	{
	case Plain: 		batteryCharge--;
	case LowCarpet:		batteryCharge -= 2;
	case HighCarpet:	batteryCharge -= 3;
	default:			return null;
	}*/
	
	/*
	 * This calculation logic was already contained in SurfaceSensor, it only needs to be called from there.
	 */
	public void move(int from, int to)
	{
		batteryLoss += from;
		batteryLoss += to;
		
		batteryLoss = batteryLoss / 2;
		batteryCharge -= batteryLoss;
		batteryLoss = 0;
		updateReport();
	}
	
	public void chargeCS()
	{
		if (myCurrentFloor == floorType.ChargingStation)
			batteryCharge = 100;
	}
	
	public void setMyCurrentFloor(floorType f){
		this.myCurrentFloor = f;
	}
	
	public static long getCharge()
	{
		return batteryCharge;
	}
	
	public static void updateReport()
	{
		Reporter.getInstance().setCurrentBattery(getCharge());
	}

}
