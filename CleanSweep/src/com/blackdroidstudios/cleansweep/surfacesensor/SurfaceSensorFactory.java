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
		Tile st = new Floor(t.getX(), t.getY(), t.getFloorType(), t.getColor(), t.getDirt());
		Sensor sensor = new Sensor(st);
		return sensor;
	}
}
