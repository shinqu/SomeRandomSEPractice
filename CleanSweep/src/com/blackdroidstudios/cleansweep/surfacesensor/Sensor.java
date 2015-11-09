package com.blackdroidstudios.cleansweep.surfacesensor;

/**
 * @author Steven Kiley
 */

import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;

class Sensor implements SurfaceSensor
{
	private Tile current;
	private Tile previous;
		
	Sensor(Tile current)
	{
		this.current = current;
		this.previous = null;
	}
	
	public int getCurrent() 
	{
		return getValue(current.getFloorType());
	}
	
	public int getTarget() 
	{
		return getValue(previous.getFloorType());
	}
	
	
	
	/**
	 * @author Steven Kiley
	 * @param t Type of floor to be evaluated
	 * @return The cost of the floor Type
	 */
	int getValue(floorType t)
	{
		//Instead of having sooooo many ifs, use a switch case (Switch works best with enums! :D)
		//I don't know if you've used switch, but it's basically like having many ifs, but in an elegant way!
		//Note: switch doesn't work with strings :(
		switch(t)
		{
		case Plain:
			return 1;
		case LowCarpet:
			return 2;
		case HighCarpet:
			return 3;
		case ChargingStation:
			return 5;
		default:
			return -1;
		}
	}

	@Override
	public int getTileCost(Tile _t)
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
	
	public int moveCost(Tile from, Tile to)
	{
		return ((getTileCost(from) + getTileCost(to))/2);
	}
	
	
}

