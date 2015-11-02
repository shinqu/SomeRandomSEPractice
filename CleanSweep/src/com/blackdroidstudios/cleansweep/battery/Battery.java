package com.blackdroidstudios.cleansweep.battery;

import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;

public class Battery 
{	
	private static int batteryCharge = 100;
	private int batteryLoss = 0;
	private floorType myCurrentFloor;
	
	
	/*switch(myCurrentFloor)
	{
	case Plain: 		batteryCharge--;
	case LowCarpet:		batteryCharge -= 2;
	case HighCarpet:	batteryCharge -= 3;
	default:			return null;
	}*/
	
	void move(floorType from, floorType to)
	{
		if (from == floorType.Plain)
			batteryLoss ++;
		else if (from == floorType.LowCarpet)
			batteryLoss += 2;
		else if (from == floorType.HighCarpet)
			batteryLoss += 3;
		
		if (to == floorType.Plain)
			batteryLoss ++;
		else if (to == floorType.LowCarpet)
			batteryLoss += 2;
		else if (to == floorType.HighCarpet)
			batteryLoss += 3;
		
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
	
	public static int getCharge()
	{
		return batteryCharge;
	}
	
	public static void updateReport()
	{
		Reporter.getInstance().setCurrentBattery(getCharge());
	}

}
