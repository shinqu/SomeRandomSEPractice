package com.blackdroidstudios.cleansweep.movementsensor;

import java.util.ArrayList;

import com.blackdroidstudios.cleansweep.gui.GUIObserver;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;

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
			}else
			{
				//There are no open neighbours, return to the nearest possible!
				
			}
		}else
		{
			//Move it!!
			if(currentTile != path.get(0))
			{
				
				//Move
				currentTile = path.get(0);
				//Register new Tile
				map.registerTile(currentTile);
				//Register all open tiles
				map.registerOpenTiles(currentTile.getNeighbours());
				//Update the battery usage here...
				
				//Remove the current tile from the path
				path.remove(0);
				//Report it to Log
				//Reporter.updatePosition(getX(), getY());
				//Update in GUI
				GUIObserver.updateCleanSweepPos(currentTile);
			}else
			{
				map.registerOpenTiles(currentTile.getNeighbours());
				map.registerTile(currentTile);
				path.remove(0);
			}
			Reporter.getInstance().setCurrentLocX(currentTile.getX());
			Reporter.getInstance().setCurrentLocY(currentTile.getY());
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
		map.registerOpenTiles(currentTile.getNeighbours());
		GUIObserver.updateCleanSweepPos(_tile);
	}
	/**
	 * @return Return the path to be traveled
	 */
	public ArrayList<Tile> getPath()
	{
		return path;
	}
	
	public ArrayList<Tile> getVisitedTiles()
	{
		return map.getVisitedTiles();
	}
	
	public ArrayList<Tile> getOpenTiles()
	{
		return map.getOpenTiles();
	}
	
	/**
	 * Accessor for MovementMap findReturnPath() method
	 * @return ArrayList<Tile>
	 * @param tile from, tile to
	 */
	public ReturnPathCarrier chooseCS(Tile from){
		return map.chooseCS(from);
	}
	
	public void setCurrentTile(Tile t){
		this.currentTile = t;
		GUIObserver.updateCleanSweepPos(currentTile);
	}
	
}
