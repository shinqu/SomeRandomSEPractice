package com.blackdroidstudios.cleansweep.movementsensor;

import java.util.ArrayList;
import java.util.Collections;

import com.blackdroidstudios.cleansweep.gui.GUIObserver;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;
import com.blackdroidstudios.cleansweep.surfacesensor.SurfaceSensor;

/**
 * 
 * @author Armando Garcin
 *<p>MovementMap will store all tiles that the vacuum cleaner has visited, and will also return a path to a desired tile</p>
 *It is designed to be called by any sensor and get information on visited Tiles, or even find a path between two tiles!
 */

public class MovementMap 
{
	//Variables
	private SurfaceSensor surfaceSensor;
	private ArrayList<Tile> visitedTiles;
	private ArrayList<Tile> openTiles;
	private ArrayList<Tile> chargingStations;
	private ArrayList<Tile> obstacleTiles;
	private ArrayList<Tile> dirtyTiles;

	//Default Constructor
	public MovementMap(SurfaceSensor _ss)
	{
		surfaceSensor = _ss;
		visitedTiles = new ArrayList<Tile>();
		openTiles = new ArrayList<Tile>();
		chargingStations = new ArrayList<Tile>();
		obstacleTiles = new ArrayList<Tile>();
		dirtyTiles = new ArrayList<Tile>();
	}
	
	/**
	 * @return The registered tiles that the vacuum cleaner has visited
	 */
	public ArrayList<Tile> getVisitedTiles()
	{
		return visitedTiles;
	}
	
	public ArrayList<Tile> getOpenTiles()
	{
		return openTiles;
	}
	
	public ArrayList<Tile> getDirtyTiles()
	{
		return dirtyTiles;
	}
	
	/**
	 * <p>Use this function to check if a Tile has been visited. (Pretty handy!)</p>
	 * @param _tile The Tile to check if visited before
	 * @return True if the tile has been visited before
	 */
	public boolean isTileVisited(Tile _tile)
	{
		if(visitedTiles.contains(_tile))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Use this to add a new tile to the visited list. (Don't worry, if the tile is already in the list, it won't be added)
	 * @param _newTile The tile to be registered
	 */
	public void registerTile(Tile _newTile)
	{
		if(!visitedTiles.contains(_newTile))
		{
			visitedTiles.add(_newTile);
			GUIObserver.addNewTile(_newTile);
			//Eliminate it from openTiles list if it's there
			if(openTiles.contains(_newTile))
			{
				openTiles.remove(_newTile);
			}
			
			if(_newTile.getFloorType() == floorType.ChargingStation && !chargingStations.contains(_newTile))
			{
				chargingStations.add(_newTile);
			}
		}	
		
	}
	/**
	 * Use this to add a new tile to the Open list. (Don't worry, if the tile is already in the list, it won't be added)
	 * @param _newTile The tile to be registered
	 */
	public void registerOpen(Tile _tile)
	{
		if(!openTiles.contains(_tile) && !visitedTiles.contains(_tile))
		{
			openTiles.add(_tile);
			GUIObserver.addNewTile(_tile);
			
			if(_tile.getFloorType() == floorType.ChargingStation && !chargingStations.contains(_tile))
			{
				chargingStations.add(_tile);
			}
		}
	}
	
	/**
	 * This function will help us record open adjascent tiles, so if we ever get stuck, we can always go back to the nearest one.
	 * In the event there are no more open tiles, it means we have recorded ALL of the floor.
	 * @param _openTile
	 */
	public void registerOpenTiles(ArrayList<Tile> _openTile)
	{
		for(Tile _t : _openTile)
		{
			if(_t.getTileType() == tileType.Passable && !openTiles.contains(_t) && !visitedTiles.contains(_t))
			{
				openTiles.add(_t);
				GUIObserver.addNewTile(_t);
			}else if(_t.getTileType() == tileType.Impassable && !obstacleTiles.contains(_t))
			{
				obstacleTiles.add(_t);
				GUIObserver.addNewTile(_t);
			}
		}
	}
	/**
	 * This method will return a path to the nearest charging station available
	 */
	public ArrayList<Tile> returnToCS(Tile _current)
	{
		ArrayList<Tile> fPath = new ArrayList<Tile>();
		Tile _result = null;
		for(Tile _cs : chargingStations)
		{
			if(_result != null)
			{
				if(getDistanceCost(_current, _cs) < getDistanceCost(_current, _result))
				{
					_result = _cs;
				}
			}else
			{
				_result = _cs;
			}
		}
		return findPath(_current, _result);
	}
	
	public ArrayList<Tile> findOpenTile(Tile _tile)
	{
		ArrayList<Tile> tList = new ArrayList<Tile>();
		
		Tile tTile = null;
		
		for(Tile _t : openTiles)
		{
			if(tTile != null)
			{
				if(getDistanceCost(_tile, _t) < getDistanceCost(_tile, tTile))
				{
					tTile = _t;
				}
			}else
			{
				tTile = _t;
			}
		}
		tList = findPath(_tile, tTile);
		return tList;
	}
	
	
	/**
	 * <p>DO NOT EDIT THIS!!!! (Yes, you!! ò.ó) If you do, I'll find you and hit you with the Warhammer 40k 6th. Ed. Hardcover Rulebook.</p>
	 * <p>This is the function that will magically find you a path between the current tile, and the destination.</p>
	 * @param _startingTile The tile you are in right now
	 * @param _goalTile The Tile you want to get to
	 * @return Magic happens, and returns a list that contains the path to be traveled
	 */
	public ArrayList<Tile> findPath(Tile _startingTile, Tile _goalTile)
	{
		ArrayList<Tile> openList = new ArrayList<Tile>();
		ArrayList<Tile> closedList = new ArrayList<Tile>();
		ArrayList<Tile> finalPath = new ArrayList<Tile>();
		
		//Let's roll baby!
		//First we add the starting tile to the open List
		openList.add(_startingTile);
		
		//Let's find a path!
		while(!openList.isEmpty())
		{
			Tile selectedTile = null;
			
			//Step 1
			//Get the lowest cost (and open) tile from Open List
			for(Tile _t : openList)
			{
				if(selectedTile != null)
				{
					if(getFCost(_t, _startingTile, _goalTile) < getFCost(selectedTile, _startingTile, _goalTile))
					{
						selectedTile = _t;
					}
				}else
				{
					selectedTile = _t;
				}
			}
			
			//Step 2
			//Check if the selected tile is the goal!
			if(selectedTile == _goalTile)
			{
				finalPath = reconstructPath(closedList, _startingTile, _goalTile);
				break;
			}
			
			//Step 3
			//Update the closed and open lists
			openList.remove(selectedTile);
			closedList.add(selectedTile);
			
			//Step 4
			//Add all available neighbours to the OpenList
			for(Tile _t : selectedTile.getNeighbours())
			{
				if(!closedList.contains(_t) && _t.getTileType() == tileType.Passable)
				{
					openList.add(_t);
				}
			}
		}
		
		return finalPath;
	}
	/**
	 * <p>This is a top secret function, don't use it!!</p>
	 * @param _foundPath List of Tiles that lead to the goal
	 * @return The reconstructed Path
	 * @see https://www.youtube.com/watch?v=7qXXWHfJha4
	 */
	private ArrayList<Tile> reconstructPath(ArrayList<Tile> _foundPath, Tile _start, Tile _goal)
	{
		ArrayList<Tile> finalPath = new ArrayList<Tile>();
		
		Collections.reverse(_foundPath);
		//If the closed List is empty, just return a simple list
		if(!_foundPath.isEmpty())
		{
			finalPath.add(_goal);
			Tile curr = _goal;
			for(Tile _t : _foundPath)
			{
				if(_t != curr)
				{
					for(Tile _n : _t.getNeighbours())
					{
						if(finalPath.contains(_n) && _n == curr && !finalPath.contains(_t))
						{
							finalPath.add(_t);
							curr = _t;
							break;
						}
					}
				}
			}
		}else
		{
			finalPath.add(_start);
		}
		
		//Reverse the List
		Collections.reverse(finalPath);
		
		return finalPath;
	}
	
	private int getDistanceCost(Tile _from, Tile _to)
	{
		return findPath(_from, _to).size();
	}
	
	private int getFCost(Tile _current, Tile _start, Tile _goal)
	{
		return getGCost(_current, _start) + getHCost(_current, _goal);
	}
	
	private int getGCost(Tile _current, Tile _start)
	{
		return((Math.abs(_current.getX() - _start.getX()) + Math.abs(_current.getY() - _start.getY())) + surfaceSensor.getTileCost(_current));
	}
	
	private int getHCost(Tile _current, Tile _goal)
	{
		return ((Math.abs(_current.getX() - _goal.getX()) + Math.abs(_current.getY() - _goal.getY())) + surfaceSensor.getTileCost(_current));
	}
	
	
	
}
