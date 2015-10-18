package com.blackdroidstudios.cleansweep.movementsensor;

import java.util.ArrayList;

import com.blackdroidstudios.cleansweep.map.Tile;

/**
 * @Author Armando Garcin
 * 
 * The Movement Control will be in charge as of where the Vacuum Cleaner will go next!
 */

public class MovementControl 
{
	//Variables
	private MovementMap map;
	private ArrayList<Tile> path;
	private Tile currentTile;
	
	public MovementControl()
	{
		map = new MovementMap();
		currentTile = null;
	}
	
	public Tile getCurrentTile()
	{
		return currentTile;
	}
	
	public void Move()
	{
		if(path.isEmpty())
		{
			
		}
	}
	
	/**
	 * Set the tile we are on at the beginning
	 * @param _tile Starting tile
	 */
	public void setStartTile(Tile _tile)
	{
		currentTile = _tile;
		map.registerTile(_tile);
	}
}
