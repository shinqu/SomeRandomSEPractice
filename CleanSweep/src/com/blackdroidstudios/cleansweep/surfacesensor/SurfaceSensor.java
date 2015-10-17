package com.blackdroidstudios.cleansweep.surfacesensor;

/**
 * @author Steven Kiley
 */

import com.blackdroidstudios.cleansweep.map.Tile;

public interface SurfaceSensor {
	int getCurrent();
	int getPrevious();
	int getNorth();
	int getSouth();
	int getEast();
	int getWest();
	
	void registerMove(int x, int y);
	void setCurrent(Tile s);
	void setNeighbor(Tile s);
}
