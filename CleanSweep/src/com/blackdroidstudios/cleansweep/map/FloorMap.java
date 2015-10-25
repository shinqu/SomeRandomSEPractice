package com.blackdroidstudios.cleansweep.map;

/**
 * 
 * @author Armando Garcin
 *
 *<p>This class is the Main controller for the entire Map!</p>
 */

public class FloorMap 
{
	//Statics
	public static final int FLOOR_SIZE_X = 15;
	public static final int FLOOR_SIZE_Y = 15;
	
	//Variables
	private FloorGenerator floorGen;
	private Tile[][] map;
	
	
	//Constructor
	public FloorMap()
	{
		floorGen = new FloorGenerator();
		map = floorGen.generateEmptyMap();
	}
	
	public Tile[][] getMap()
	{
		return map;
	}
}
