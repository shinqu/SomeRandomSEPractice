package com.blackdroidstudios.cleansweep.dirtsensor;

import com.blackdroidstudios.cleansweep.map.*;


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
	public int detectDirt(Tile _tile) ;
	
	/**
	 * Boolean Method to help logic of whether a tile has dirt or not.  
	 */
	public boolean cleanCheck(Tile _tile) ;
	
	
	public void dumpDirt();
	
	/**
	 * Cleaning function of dirt sensor.  Removes one unit of dirt and registers cell as dirty if a unit remains.
	 * Need to figure out algorithm to trace back to cells still containing dirt.
	 * @return
	 */
	public void cleanDirt(Tile _tile);
	
	public boolean isFull();

}
