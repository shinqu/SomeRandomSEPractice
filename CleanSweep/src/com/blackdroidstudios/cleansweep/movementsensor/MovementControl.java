package com.blackdroidstudios.cleansweep.movementsensor;

import java.util.ArrayList;

import com.blackdroidstudios.cleansweep.gui.GUIObserver;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;
import com.blackdroidstudios.cleansweep.surfacesensor.SurfaceSensor;
import com.blackdroidstudios.cleansweep.surfacesensor.SurfaceSensorFactory;

/**
 * @Author Armando Garcin
 * 
 * The Movement Control will be in charge as of where the Vacuum Cleaner will go next!
 */

public class MovementControl 
{
	//Variables
	private MovementMap map;
	private SurfaceSensor surfaceSensor;
	private ArrayList<Tile> path;
	private Tile currentTile;
	private boolean returningToCS;
	
	public MovementControl()
	{
		path = new ArrayList<Tile>();
		currentTile = null;
		returningToCS = false;
	}
	
	public Tile getCurrentTile()
	{
		return currentTile;
	}
	
	public int Move()
	{
		int moveCount = 0;
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
				path = map.findOpenTile(currentTile);
			}
		}else
		{
			//Move it!!
			if(currentTile != path.get(0))
			{
				//Register the cost
				moveCount = surfaceSensor.moveCost(currentTile, path.get(0));
				
				//Move
				currentTile = path.get(0);
				//Register new Tile
				map.registerTile(currentTile);
				//Register all open tiles
				map.registerOpenTiles(currentTile.getNeighbours());
				
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
			
			//Small check if we are at a charging station
			if(currentTile.getFloorType() == Tile.floorType.ChargingStation)
			{
				returningToCS = false;
			}
			
			Reporter.getInstance().setCurrentLocX(currentTile.getX());
			Reporter.getInstance().setCurrentLocY(currentTile.getY());
		}
		
		return moveCount;
	}
	
	/**
	 * Set the tile we are on at the beginning
	 * @param _tile Starting tile
	 */
	public void setStartTile(Tile _tile)
	{
		currentTile = _tile;
		try {
			surfaceSensor = SurfaceSensorFactory.getNewSensor(currentTile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map = new MovementMap(surfaceSensor);
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
	
	public void returnToCS()
	{
		if(!returningToCS)
		{
			path = map.returnToCS(currentTile);
			returningToCS = true;
		}
	}
}
