package com.blackdroidstudios.cleansweep.dirtsensor;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;

import com.blackdroidstudios.cleansweep.*;
import com.blackdroidstudios.cleansweep.gui.*;
import com.blackdroidstudios.cleansweep.map.*;
import com.blackdroidstudios.cleansweep.movementsensor.*;
import com.blackdroidstudios.cleansweep.surfacesensor.*;





/**
 * DirtSensor is used to detect dirt in CleanSweep Vaccuum. May need to retool constructor in order to deal with movement,
 * but basic functionality should be here.  Relies on public tile variables of dirt and newly added cleanState. Instances are created
 * in a factory and methods are gathered from DirtSensorInter Interface.  
 * @author brooney
 *
 */
public class DirtSensor implements DirtSensorInter  //testing out git
{
	private int dirtDetected;
	public boolean cleanState;
	private Tile currentTile;

	/**
	 * Can't hurt to have a 
	 */
	public DirtSensor()
	{
		dirtDetected = 0;
		cleanState = true;
		currentTile = null;
	}
	/**
	 * Constructor Class currently run off of one single tile, would like to ultimately use floor plan as argument, 
	 * and run individual tiles as arguments through the methods. 
	 * 
	 */
	public DirtSensor(Tile tile) 
	{
		dirtDetected = tile.getDirt();
		currentTile = tile;
	}

	/**
	 * Method used to check if dirt is contained on a tile. Updates private variable dirtDetected with
	 * how many units of dirt are found. 
	 */
	public int detectDirt() {
		dirtDetected = currentTile.getDirt();
		return dirtDetected;
	}
	
	public int detectDirt(Tile _tile)
	{
		currentTile = _tile;
		
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
	public String registerCell() //create array list
	{  
		
		/*if (cleanState = false) {
			currentTile.cellState = "D";
		}
		
		else {
			currentTile.cellState = "C";
		}*/
		
		return "";
	}

	@Override
	public int cleanDirt() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	/**
	 * Created this main just for example purposes of how DirtSensor can be used.
	 * @param args
	 */
	/*public void main (int[] args) { //example of how DirtSensor class can be used
		Floor tile2 = new Floor (2, 3, Tile.floorType.Plain);
		tile2.dirt = 4;
		DirtSensor ds = new DirtSensor(tile2);
		ds.detectDirt();
	}*/

}
