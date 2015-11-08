package com.blackdroidstudios.cleansweep.battery;

import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;

public class Battery 
{	
	private static int batteryCharge = 100;
	private floorType myCurrentFloor;
	
	public void moveCost(Tile from, Tile to)
	{
		int batteryLoss = ((getTileCost(from) + getTileCost(to))/2);
		batteryCharge -= batteryLoss;
		updateReport();
	}
	public static int getCost(Tile from, Tile to)
	{
		return ((getTileCost(from) + getTileCost(to))/2);
	}
	
	public static int getTileCost(Tile _t)
	{
		int result = 0;
		switch(_t.getFloorType())
		{
		case Plain:
		case ChargingStation:
			result = 1;
			break;
		case LowCarpet:
			result = 2;
			break;
		case HighCarpet:
			result = 3;
			break;
		}
		return result;
	}
	
	public void chargeCS()
	{
		if (myCurrentFloor == floorType.ChargingStation)
		{
			batteryCharge = 100;
		}
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
