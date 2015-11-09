package com.blackdroidstudios.cleansweep.surfacesensor;

/**
 * @author Steven Kiley
 */

import com.blackdroidstudios.cleansweep.map.Floor;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;

public class SurfaceSensorFactory 
{
	public static SurfaceSensor getNewSensor(Tile t) throws Exception
	{
		if(t.getTileType() == tileType.Impassable)
		{
			throw new Exception();
		}
		Sensor sensor = new Sensor(t);
		return sensor;
	}
}
