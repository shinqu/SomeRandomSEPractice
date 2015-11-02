package com.blackdroidstudios.cleansweep.map;

/**
 * @author Neha Beke
 * @author Armando Garcin
 * 
 * <p>Floor Generator is supposed to create the map and return it to 
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import com.blackdroidstudios.cleansweep.map.Tile.floorType;

public class FloorGenerator 
{
	
	//Constructor
	public FloorGenerator()
	{
		
	}
	
	/**
	 * <p>This generates a map with no obstacles and just plain floor tiles</p>
	 * <p>You can use this to test some code you created :)</p>
	 * @return One empty map with just plain floor
	 */
	public Tile[][] generateEmptyMap()
	{
		Tile[][] newMap = new Tile[FloorMap.FLOOR_SIZE_X][FloorMap.FLOOR_SIZE_Y];
		
		for(int _x = 0; _x < FloorMap.FLOOR_SIZE_X; _x++)
		{
			for(int _y = 0; _y < FloorMap.FLOOR_SIZE_Y; _y++)
			{
				if(_x == 0 && _y == 0)
				{
					newMap[_x][_y] = new Floor(_x, _y, floorType.ChargingStation, Color.CYAN, 0);
				}else if(_x == 0 && _y == 3)
				{
					newMap[_x][_y] = new Obstacle(_x, _y, null);
				}else
				{
					Random rnd = new Random();//To be removed
					newMap[_x][_y] = new Floor(_x, _y, floorType.Plain, Color.LIGHT_GRAY, rnd.nextInt(10) - 7);
				}
				
			}
		}
		
		
		
		
		addNeighbours(newMap);
		
		return newMap;
	}
	
	private void addNeighbours(Tile[][] tileCollection)
	{
		for(int i = 0; i < FloorMap.FLOOR_SIZE_X; i++)
		{
			for(int j = 0; j < FloorMap.FLOOR_SIZE_Y; j++)
			{
				if(j + 1 < FloorMap.FLOOR_SIZE_Y)
				{
					tileCollection[i][j].addNeighbour(tileCollection[i][j + 1]);
				}
				if(j - 1 >= 0)
				{
					tileCollection[i][j].addNeighbour(tileCollection[i][j - 1]);
				}
				if(i + 1 < FloorMap.FLOOR_SIZE_X)
				{
					tileCollection[i][j].addNeighbour(tileCollection[i + 1][j]);
				}
				if(i - 1 >= 0)
				{
					tileCollection[i][j].addNeighbour(tileCollection[i - 1][j]);
				}
				
			}
		}
	}
}
