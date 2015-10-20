package com.blackdroidstudios.cleansweep.movementsensor;

import java.util.ArrayList;
import java.util.Collections;

import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;

/**
 * 
 * @author Armando Garcin
 *<p>MovementMap will store all tiles that the vacuum cleaner has visited, and will also return a path to a desired tile</p>
 *It is designed to be called by any sensor and get information on visited Tiles, or even find a path between two tiles!
 */

public class MovementMap 
{
	//Variables
	private ArrayList<Tile> visitedTiles;
	private ArrayList<Tile> openTiles;
	private ArrayList<Tile> chargingStations;

	//Default Constructor
	public MovementMap()
	{
		visitedTiles = new ArrayList<Tile>();
		openTiles = new ArrayList<Tile>();
		chargingStations = new ArrayList<Tile>();
	}
	
	/**
	 * @return The registered tiles that the vacuum cleaner has visited
	 */
	public ArrayList<Tile> getVisitedTiles()
	{
		return visitedTiles;
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
			//Eliminate it from openTiles list if it's there
			if(openTiles.contains(_newTile))
			{
				openTiles.remove(_newTile);
			}
			
			if(_newTile.getFloorType() == floorType.ChargingStation)
			{
				chargingStations.add(_newTile);
			}
		}	
		
	}
	
	private void registerOpenTile(Tile _openTile)
	{
		openTiles.add(_openTile);
	}
	
	public void returnToCS()
	{
		for(Tile _cs : chargingStations)
		{
			
		}
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
		
		Tile currentTile = null;
		
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
					if(getFCost(_t, _startingTile, _goalTile) < getFCost(_t, _startingTile, _goalTile))
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
	public ArrayList<Tile> findPath(Tile _currentTile)
	{	
		Tile newTargetTile = null;
		
		return null;
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
		
		//If the closed List is empty, just return a simple list
		if(!_foundPath.isEmpty())
		{
			finalPath.add(_goal);
			
		}else
		{
			finalPath.add(_start);
		}
		
		//Reverse the List
		Collections.reverse(finalPath);
		
		return finalPath;
	}
	
	private int getFCost(Tile _current, Tile _start, Tile _goal)
	{
		return getGCost(_current, _start) + getHCost(_current, _goal);
	}
	
	private int getGCost(Tile _current, Tile _start)
	{
		return(Math.abs(_current.getX() - _start.getX()) + Math.abs(_current.getY() - _start.getY()));
	}
	
	private int getHCost(Tile _current, Tile _goal)
	{
		return (Math.abs(_current.getX() - _goal.getX()) + Math.abs(_current.getY() - _goal.getY()));
	}
	
	
	
}
