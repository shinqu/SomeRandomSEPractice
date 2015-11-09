package com.blackdroidstudios.cleansweep.map;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import com.blackdroidstudios.cleansweep.map.Tile.floorType;

public class MapReader 
{
	//Statics
	private static final char PLAIN = 'P';
	private static final char LOW_CARPET = 'L';
	private static final char HIGH_CARPET = 'H';
	private static final char CHARGING_STATION = 'C';
	private static final char WALL = 'W';
	private static final char STAIRS = 'S';
	//Variables 
	private String path;
	
	public MapReader()
	{
		path = "src/resources/maptexts/OfficialMap.txt";
		
	}
	
	public Tile[][] readMap()
	{
		Tile[][] newMap = new Tile[FloorMap.FLOOR_SIZE_X][FloorMap.FLOOR_SIZE_Y];
		String line = "";
		//Step 1: Try to read the file
		try 
		{
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			
			int yCounter = 0;
			//Step 2: Success! Now read the line and create the map respectively
			while((line = br.readLine()) != null)
			{
				for(int i = 0; i < line.length(); i++)
				{
					newMap[i][yCounter] = readMapLine(line.charAt(i), i, yCounter);
				}
				yCounter++;
			}
			
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			newMap = null;
		}
		
		
		
		return newMap;
	}
	
	private Tile readMapLine(char c, int _x, int _y)
	{
		Tile newTile = null;
		Random rnd = new Random();

		if(c == PLAIN)
		{
			newTile = new Floor(_x, _y, floorType.Plain, Color.LIGHT_GRAY, rnd.nextInt(10) - 7);
		}else if(c == WALL || c == STAIRS)
		{
			newTile = new Obstacle(_x, _y);
		}else if(c == LOW_CARPET)
		{
			newTile = new Floor(_x, _y, floorType.LowCarpet, Color.GRAY, rnd.nextInt(10) - 6);
		}else if(c == HIGH_CARPET)
		{
			newTile = new Floor(_x, _y, floorType.HighCarpet, Color.DARK_GRAY, rnd.nextInt(10) - 5);
		}else if(c == CHARGING_STATION)
		{
			newTile = new Floor(_x, _y, floorType.ChargingStation, Color.BLUE, 0);
		}
		
		return newTile;
	}
}
