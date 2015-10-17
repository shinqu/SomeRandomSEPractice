package com.blackdroidstudios.cleansweep.map;

import java.awt.Color;
import java.util.ArrayList;

/*
 * @Author Neha Beke
 * 
 * 
 */
public class FloorGenerator 
{
	
	//Tile myTile;
	public static Tile[][] tileCollection = new Tile[FloorMap.FLOOR_SIZE_X][FloorMap.FLOOR_SIZE_Y];
	int x = 0;
	int y = 0;
	
	//Constructor
	public FloorGenerator()
	{
		boolean changeY = false;
		for(int i = 0; i < FloorMap.FLOOR_SIZE_X; i++){
			x = 0;
			if(changeY)
				y = y + 130;
				
			for(int j = 0; j < FloorMap.FLOOR_SIZE_Y; j++){
				tileCollection[i][j] = new Floor(x, y, Tile.floorType.Plain, Color.GREEN, 4);
				x = x + 130;
				changeY = true;
			}
		}
		
		for(int i = 0; i < FloorMap.FLOOR_SIZE_X; i++){
			for(int j = 0; j < FloorMap.FLOOR_SIZE_Y; j++){
				if(i - 1 < FloorMap.FLOOR_SIZE_X && i - 1 >= 0)
					tileCollection[i][j].addNeighbour(tileCollection[i - 1][j]);
				
				if(j + 1 < FloorMap.FLOOR_SIZE_Y)
					tileCollection[i][j].addNeighbour(tileCollection[i][j + 1]);
				
				if(j - 1 < FloorMap.FLOOR_SIZE_Y && j - 1 >= 0)
					tileCollection[i][j].addNeighbour(tileCollection[i][j - 1]);
				
				if(i + 1 < FloorMap.FLOOR_SIZE_X)
					tileCollection[i][j].addNeighbour(tileCollection[i + 1][j]);
			}
		}
	}
}
