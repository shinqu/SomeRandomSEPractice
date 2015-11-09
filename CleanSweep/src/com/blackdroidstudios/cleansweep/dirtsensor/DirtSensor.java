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
	
	public boolean cleanState;
	private int currentStorage;
	public boolean stopCleaning;
	//private ArrayList<Tile> dirtyTiles = new ArrayList<Tile>();
	private ArrayList<Tile> cleanedTiles = new ArrayList<Tile>();

	/**
	 * Can't hurt to have a default constructor
	 */
	public DirtSensor()
	{
		cleanState = true;
	}

	/**
	 * Method used to check if dirt is contained on a tile. Updates private variable dirtDetected with
	 * how many units of dirt are found. 
	 */
	public int detectDirt(Tile _tile) 
	{
		int dirtDetected = _tile.getDirt();
		return dirtDetected;
	}
	
	/**
	 * Boolean Method to help logic of whether a tile has dirt or not.  
	 */
	public boolean cleanCheck(Tile _tile) 
	{ 
		 //updates dirtDetected variable
		if (detectDirt(_tile) > 0) 
		{
			cleanDirt(_tile);
		}
		else 
		{
			cleanState = true;
		}
		Reporter.getInstance().setCurrentDirtLvl(currentStorage);
		return cleanState;
	}

	@Override
	public void cleanDirt(Tile _tile) 
	{
		Floor _f = (Floor)_tile;
		if((currentStorage + _tile.getDirt()) <= DIRT_MAX_CAPACITY)
		{
			currentStorage += _tile.getDirt();
			_f.cleanFloor(_tile.getDirt());
			cleanState = true;
		}else
		{
			cleanState = false;
		}
		
	}

	@Override
	public void dumpDirt() 
	{
		// TODO Auto-generated method stub
		currentStorage = 0;
	}

	@Override
	public boolean isFull() 
	{
		if(currentStorage == DIRT_MAX_CAPACITY)
		{
			return true;
		}else
		{
			return false;
		}
	}
}
