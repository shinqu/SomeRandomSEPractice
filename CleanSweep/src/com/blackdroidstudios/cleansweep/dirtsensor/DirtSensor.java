package com.blackdroidstudios.cleansweep.dirtsensor;

import com.blackdroidstudios.cleansweep.map.*;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;
import java.util.ArrayList;





/**
 * DirtSensor is used to detect dirt in CleanSweep Vaccuum. May need to retool constructor in order to deal with movement,
 * but basic functionality should be here.  Relies on public tile variables of dirt and newly added cleanState. Instances are created
 * in a factory and methods are gathered from DirtSensorInter Interface.  
 * @author brooney
 *
 */
public class DirtSensor implements DirtSensorInter  {
	//Static
	public static final int DIRT_MAX_CAPACITY = 50;
	
	private int dirtDetected;
	public boolean cleanState;
	private Tile currentTile;
	private int currentStorage;
	public boolean stopCleaning;
	//private ArrayList<Tile> dirtyTiles = new ArrayList<Tile>();
	private ArrayList<Tile> cleanedTiles = new ArrayList<Tile>();

	/**
	 * Can't hurt to have a default constructor
	 */
	public DirtSensor()
	{
		dirtDetected = 0;
		cleanState = true;
		currentTile = null;
	//	dirtyTiles.add(com.blackdroidstudios.cleansweep.map.FloorGenerator.generateEmptyMap());  // Not sure what I want to do here
	}

	
	public int detectDirt()
	{
		
		dirtDetected = currentTile.getDirt();
		
		return dirtDetected;
	}
	
	/**
	 * Boolean Method to help logic of whether a tile has dirt or not.  
	 */
	public boolean cleanCheck() 
	{ 
		detectDirt();  //updates dirtDetected variable
		if (dirtDetected > 0) 
		{
			cleanState = false;
		}
		else 
		{
			cleanState = true;	
		}
		return cleanState;
	}
	
	/**
	 * Implements cleanCheck logic to print to Tile to display whether clean or dirty.  Can possibly add
	 * color coding to simulation.  
	 */
	public void registerCell() //create array list
	{  
		if (cleanState = true) {	
		cleanedTiles.add(currentTile);	 }
		
		else {
			Reporter.getInstance().printGUI("Cannot Register Dirty Cell as Clean");
		}
		
		
	}

	@Override
	public int cleanDirt() 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean stopCleaning() {
		boolean tempStopCleaning;

		// Check if clean sweep has visited every accessible location on the current floor.
		tempStopCleaning = checkEveryLocIsCleaned();
		stopCleaning = tempStopCleaning;
		if (tempStopCleaning == true) {
			return true;
		}
		
		// Check if 50-unit dirt capacity has been met.
		if (currentStorage >= 50) {
			tempStopCleaning = true;
			return true;
		}
			
		// Check if clean sweep only has enough power remaining to
		// return to its charging station. See
		// Power Management for more details.
		
		return false;
	}

	/**
	 * Checks if clean sweep has visited every accessible location on the
	 * current floor.
	 * 
	 * @return
	 */
	public boolean checkEveryLocIsCleaned() {
		FloorGenerator floor = new FloorGenerator();
		Tile[][] floorTileCollection = floor.generateEmptyMap();

		boolean tempStopCleaning = true;
		for (int i = 0; i < FloorMap.FLOOR_SIZE_X; i++) {
			for (int j = 0; j < FloorMap.FLOOR_SIZE_Y; j++) {
				Tile tile = floorTileCollection[i][j];
				if (tile.getDirt() > 0) {
					tempStopCleaning = false;
					break;
				}
			}
		}
		return tempStopCleaning;
	}
	@Override
	public void cleanTile(Tile _tile) 
	{
		// TODO Auto-generated method stub
		if(_tile.getDirt() > 0)
		{
			Floor _f = (Floor)_tile;
			_f.cleanFloor();
		}
	}
}
