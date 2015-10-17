package com.blackdroidstudios.cleansweep.dirtsensor;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.*;
import com.blackdroidstudios.cleansweep.gui.*;
import com.blackdroidstudios.cleansweep.main.*;
import com.blackdroidstudios.cleansweep.map.*;
import com.blackdroidstudios.cleansweep.movementsensor.*;//test
import com.blackdroidstudios.cleansweep.surfacesensor.*;

/**
 * Factory class for DirtSensor.  Creates a new instance of a DirtSensor.  Not sure what the best argument is to run through it.  
 * @author brooney
 *
 */
public class DirtSensorFactory 
{
	
	public static DirtSensorInter create(Floor tile)
	{
		return new DirtSensor(tile);
	}

}
