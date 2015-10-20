package com.blackdroidstudios.cleansweep.movementsensor;

import java.util.ArrayList;

import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;

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
		path = new ArrayList<Tile>();
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
			Tile newTarget = null;
			
			System.out.println("Creating new path");
			//Check any open neighbours
			for(Tile _tile : currentTile.getNeighbours())
			{
				if(!map.getVisitedTiles().contains(_tile) && _tile.getTileType() == tileType.Passable)
				{
					newTarget = _tile;
					break;
				}
			}
			
			//Did we find a free and open tile to move?
			if(newTarget != null)
			{
				path = map.findPath(currentTile, newTarget);
			}
		}else
		{
			//Move it!!
			System.out.println("Moving");
			currentTile = path.get(0);
			path.remove(0);
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
	/**
	 * @return Return the path to be traveled
	 */
	public ArrayList<Tile> getPath()
	{
		return path;
	}
}
