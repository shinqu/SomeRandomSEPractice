package com.blackdroidstudios.cleansweep.battery;

/**
 * @author Xin Qu
 */

import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;

public class Battery 
{	
	private static int batteryCharge = 100;
	private floorType myCurrentFloor;
	
	public void chargeCS()
	{
		batteryCharge = 100;
		updateReport();
	}
	
	public void spendBattery(int _nrg)
	{
		batteryCharge -= _nrg;
		updateReport();
	}
	
	public int getBatteryLevel()
	{
		return batteryCharge;
	}
	
	public void updateReport()
	{
		Reporter.getInstance().setCurrentBattery(getBatteryLevel());
	}

}
