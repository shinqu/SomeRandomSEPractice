package com.blackdroidstudios.cleansweep.battery;

import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;

public class Battery 
{	
	private static int batteryCharge = 100;
	floorType myCurrentFloor;
	
	/*switch(myCurrentFloor)
	{
	case Plain: 		batteryCharge--;
	case LowCarpet:		batteryCharge -= 2;
	case HighCarpet:	batteryCharge -= 3;
	default:			return null;
	}*/
	
	
	/*void calcCharge()
	{
		if (myCurrentFloor = Plain)
			batteryCharge --;
		else if (myCurrentFloor == LowCarpet)
			batteryCharge -= 2;
		else if (myCurrentFloor == HighCarpet)
			batteryCharge -= 3;
		//Charging station case
		else
		{
			//method for charging the CS
		}
	}*/
	
	public static int getCharge()
	{
		return batteryCharge;
	}
	
	public static void updateReport()
	{
		Reporter.getInstance().setCurrentBattery(getCharge());
	}

}
