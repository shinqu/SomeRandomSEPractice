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
	
	public int getPrevious() 
	{
		return getValue(previous.getFloorType());
	}
	
	public int getNorth() 
	{
		return getValue(current.getNorth().getFloorType());
	}
	
	public int getSouth() 
	{
		return getValue(current.getSouth().getFloorType());
	}
	
	public int getEast() 
	{
		return getValue(current.getEast().getFloorType());
	}
	
	public int getWest() 
	{
		return getValue(current.getWest().getFloorType());
	}
	
	public void setCurrent(Tile s) 
	{
		this.current = s;
	}
	
	public void setNeighbor(Tile s)
	{
		this.current.addNeighbour(s);
	}

	/**
	 * FILL THIS IN!!!!!!!!!
	 */
	public void registerMove(int x, int y) 
	{
		// TODO Auto-generated method stub
		
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
	
	
}

