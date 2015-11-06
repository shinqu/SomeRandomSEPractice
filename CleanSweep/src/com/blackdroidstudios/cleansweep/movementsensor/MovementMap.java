package com.blackdroidstudios.cleansweep.movementsensor;

import java.util.ArrayList;
import java.util.Collections;

import com.blackdroidstudios.cleansweep.gui.GUIObserver;
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
	private ArrayList<Tile> obstacleTiles;

	//Default Constructor
	public MovementMap()
	{
		visitedTiles = new ArrayList<Tile>();
		openTiles = new ArrayList<Tile>();
		chargingStations = new ArrayList<Tile>();
		obstacleTiles = new ArrayList<Tile>();
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
			
			if(_newTile.getFloorType() == floorType.ChargingStation)
			{
				chargingStations.add(_newTile);
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
	ReturnPathCarrier chooseCS(Tile current)
	{
		ReturnPathCarrier returnMe = null;
		ReturnPathCarrier useMe = null;
		//System.out.println("Number of charging stations: " + chargingStations.size());
		for(Tile _cs : chargingStations)
		{
			useMe = findReturnPath(current, _cs, current.getFloorType());
			if(returnMe == null){returnMe = useMe;
			if(!(returnMe == null) && useMe.getCost() < returnMe.getCost()){returnMe = useMe;}
			}
		}
		return returnMe;
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
			for(Tile _t : _foundPath)
			{
				for(Tile _n : _t.getNeighbours())
				{
					if(finalPath.contains(_n))
					{
						finalPath.add(_t);
						break;
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
	
	
	/**
	 * Method to calculate shortest return path to the charging station. From represents tile cleansweep is currently
	 * occupying. To represents the charging station. Uses a large number of temporary variables. Curr is the floortype 
	 * of the current Tile. 
	 * @param from
	 * @param to
	 * @param curr
	 * @return ArrayList<Tile> 
	 */
	ReturnPathCarrier findReturnPath(Tile from, Tile to, floorType curr){
		ReturnPathCarrier path = new ReturnPathCarrier();
		int xGoal = to.getX();
		int xCurr = from.getX();
		int yGoal = to.getY();
		int yCurr = from.getY();
		int xDir = xGoal - xCurr;
		int yDir = yGoal - yCurr;
		if(xDir < 0){xDir = -1;}
		if(xDir > 0){xDir = 1;}
		if(yDir < 0){yDir = -1;}
		if(yDir > 0){yDir = 1;}
		boolean atGoal = false;
		if(xCurr == xGoal && yCurr == yGoal){atGoal = true;} //Safety for initial move of cleansweep, where the current tile is the charging station.
		floorType currFloor = curr;
		
		while(atGoal == false){
			int xMove = xCurr + xDir;
			int yMove = yCurr + yDir;
			Tile tempX = null;
			Tile tempY = null;   // <--Both tempX and tempY should reset to null each runthrough of the while loop.
			Tile trueTemp = null;
			for(int i = 0; i < visitedTiles.size(); i++){
				trueTemp = visitedTiles.get(i);
				//check if x + dir exists
				if(trueTemp.getX() == xMove && trueTemp.getY() == yCurr && xCurr != xGoal){tempX = trueTemp;}
				//check if y + dir exists
				if(trueTemp.getX() == xCurr && trueTemp.getY() == yMove && yCurr != yGoal){tempY = trueTemp;}
				//if both exist, smaller move cost first
			}
			if(tempX != null && tempY != null){
				long costYmove = moveCost(tempX.getFloorType(), currFloor);
				long costXmove = moveCost(tempY.getFloorType(), currFloor);
				long math = costYmove - costXmove;
				if(math < 0){       //Y move is cheaper
					path.addTile(tempY);
					path.setCost(costYmove);
					yCurr = yCurr + yDir;
					currFloor = tempY.getFloorType();
				}
				if(math > 0){       //X move is cheaper
					path.addTile(tempX);
					path.setCost(costXmove);
					xCurr = xCurr + xDir;
					currFloor = tempX.getFloorType();
				}
				else{  
					path.addTile(tempX); //cost is equal, choose X move by default
					path.setCost(costXmove);
					xCurr = xCurr + xDir;
					currFloor = tempX.getFloorType();
				}
			}
			//if only one exists, just choose that one
			if(tempX != null && tempY == null){
				path.addTile(tempX);
				path.setCost(moveCost(currFloor, tempX.getFloorType()));
				xCurr = xCurr + xDir;
				currFloor = tempX.getFloorType();
			}
			if(tempY != null && tempX == null){
				path.addTile(tempY);
				path.setCost(moveCost(currFloor, tempY.getFloorType()));
				yCurr = yCurr + yDir;
				currFloor = tempY.getFloorType();
			}
			if(xCurr == xGoal && yCurr == yGoal){atGoal = true;} //have we reached the charging station coordinates?
		}
		return path;
	}
	
	
	private long moveCost(floorType from, floorType to){
		long cost = 0;
		switch(from)
		{
		case Plain:
			cost += 1;
		case LowCarpet:
			cost+= 2;
		case HighCarpet:
			cost+=3;
		case ChargingStation:
			cost+=0;	
		}
		switch(to)
		{
		case Plain:
			cost+=1;
		case LowCarpet:
			cost+=2;
		case HighCarpet:
			cost+=3;
		case ChargingStation:
			cost+=0;
		}
		cost = cost/2;
		return cost;
	}
	
}
