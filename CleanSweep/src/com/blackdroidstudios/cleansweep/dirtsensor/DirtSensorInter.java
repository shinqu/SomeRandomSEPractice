package com.blackdroidstudios.cleansweep.dirtsensor;

import com.blackdroidstudios.cleansweep.map.Floor;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.*;
import com.blackdroidstudios.cleansweep.gui.*;
import com.blackdroidstudios.cleansweep.map.*;
import com.blackdroidstudios.cleansweep.movementsensor.*;
import com.blackdroidstudios.cleansweep.surfacesensor.*;

/**
 * Interface to be used as main methods for DirtSensor class
 * @author brooney
 *
 */
public interface DirtSensorInter 
{
	
	/**
	 * Method used to check if dirt is contained on a tile. Updates private variable dirtDetected with
	 * how many units of dirt are found. 
	 */
	public int detectDirt() ;
	
	/**
	 * Boolean Method to help logic of whether a tile has dirt or not.  
	 */
	public boolean cleanCheck() ;
	
	/**
	 * Implements cleanCheck logic to print to Tile to display whether clean or dirty.  Can possibly add
	 * color coding to simulation.  
	 */
	public String registerCell();
	
	public void cleanTile(Tile _tile);
	
	/**
	 * Cleaning function of dirt sensor.  Removes one unit of dirt and registers cell as dirty if a unit remains.
	 * Need to figure out algorithm to trace back to cells still containing dirt.
	 * @return
	 */
	public int cleanDirt();

	boolean stopCleaning();

}
