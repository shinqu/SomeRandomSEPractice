package com.blackdroidstudios.cleansweep.surfacesensor;

/**
 * @author Steven Kiley
 */

import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;

public interface SurfaceSensor {
	floorType getCurrentFloorType();
	int getCurrent();
	int getPrevious();
	int getNorth();
	int getSouth();
	int getEast();
	int getWest();
	Tile getChargingStation();
	
	void setChargingStation(Tile t);
	void registerMove(Tile s);
	void setCurrent(Tile s);
	void setNeighbor(Tile s);
	
}
