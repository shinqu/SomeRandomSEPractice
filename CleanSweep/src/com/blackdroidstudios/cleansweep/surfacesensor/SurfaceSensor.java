package com.blackdroidstudios.cleansweep.surfacesensor;

/**
 * @author Steven Kiley
 */

import com.blackdroidstudios.cleansweep.map.Tile;

public interface SurfaceSensor 
{
	int getCurrent();
	int getTarget();
	
	public int getTileCost(Tile _t);
	public int moveCost(Tile from, Tile to);


}
