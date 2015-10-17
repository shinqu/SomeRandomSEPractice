package com.blackdroidstudios.cleansweep.DirtSensor;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;

import com.blackdroidstudios.cleansweep.*;
import com.blackdroidstudios.cleansweep.gui.*;
import com.blackdroidstudios.cleansweep.main.*;
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
public class DirtSensor implements DirtSensorInter {
	private int dirtDetected;
	public boolean cleanState;
	private Floor tile;

	/**
	 * Constructor Class currently run off of one single tile, would like to ultimately use floor plan as argument, 
	 * and run individual tiles as arguments through the methods. 
	 * 
	 */
	public DirtSensor(Floor tile) {
		dirtDetected = tile.getDirt();
		tile.dirt = dirtDetected;
	}

	/**
	 * Method used to check if dirt is contained on a tile. Updates private variable dirtDetected with
	 * how many units of dirt are found. 
	 */
	public int detectDirt() {
		dirtDetected = tile.getDirt();
		return dirtDetected;
	}
	
	/**
	 * Boolean Method to help logic of whether a tile has dirt or not.  
	 */
	public boolean cleanCheck() { 
		detectDirt();  //updates dirtDetected variable
		if (dirtDetected > 0) {
			cleanState = false;

		}
		else {
			cleanState = true;	
		}
		return cleanState;
	}
	
	/**
	 * Implements cleanCheck logic to print to Tile to display whether clean or dirty.  Can possibly add
	 * color coding to simulation.  
	 */
	public String registerCell() {  
		
		if (cleanState = false) {
			tile.cellState = "D";
		}
		
		else {
			tile.cellState = "C";
		}
		
		return tile.cellState;
	}
	
	/**
	 * Cleaning function of dirt sensor.  Removes one unit of dirt and registers cell as dirty if a unit remains.
	 * Need to figure out algorithm to trace back to cells still containing dirt.
	 * @return
	 */
	public int cleanDirt() { 
		if (dirtDetected > 0) {
			dirtDetected -= 1;
			System.out.println("CleanSweep has removed one unit of dirt from tile");
			System.out.println("tile still has" + dirtDetected + "units of dirt");
			registerCell();
		}
		else {
			System.out.print("There is no Dirt to clean");
			registerCell();
		}
		tile.dirt = dirtDetected;
		return dirtDetected;
	}
	
	/**
	 * Created this main just for example purposes of how DirtSensor can be used.
	 * @param args
	 */
	public void main (int[] args) { //example of how DirtSensor class can be used
		Floor tile2 = new Floor (2, 3, Tile.floorType.Plain);
		tile2.dirt = 4;
		DirtSensor ds = new DirtSensor(tile2);
		ds.detectDirt();
	}

}
