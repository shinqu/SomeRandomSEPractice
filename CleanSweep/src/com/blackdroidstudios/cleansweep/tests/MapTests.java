package com.blackdroidstudios.cleansweep.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.blackdroidstudios.cleansweep.map.FloorMap;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;

public class MapTests
{
	
	@Test
	public void TestArraySize()
	{
		try
		{
			FloorMap map = new FloorMap();
			Tile[][] testMap = map.getMap();
			assertEquals(testMap.length, 15);
		}catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void TestNeighbourSizes()
	{
		try
		{
			FloorMap map = new FloorMap();
			Tile[][] testMap = map.getMap();
			assertEquals(testMap[0][0].getNeighbours().size(), 2);
			assertEquals(testMap[0][1].getNeighbours().size(), 3);
			assertEquals(testMap[0][2].getNeighbours().size(), 3);
			assertEquals(testMap[1][0].getNeighbours().size(), 3);
			assertEquals(testMap[1][1].getNeighbours().size(), 4);
			assertEquals(testMap[1][2].getNeighbours().size(), 4);
			assertEquals(testMap[2][0].getNeighbours().size(), 3);
			assertEquals(testMap[2][1].getNeighbours().size(), 4);
			assertEquals(testMap[2][2].getNeighbours().size(), 4);
		}catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void TestFloorTypes()
	{
		try
		{
			FloorMap map = new FloorMap();
			Tile[][] testMap = map.getMap();
			assertEquals(testMap[0][0].getFloorType(), floorType.ChargingStation);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test 
	public void TestNewMap()
	{
		try
		{
			FloorMap map = new FloorMap();
			Tile[][] testMap = map.generateMap();
			assertEquals(testMap[0][0].getFloorType(), floorType.ChargingStation);
			assertEquals(testMap[1][0].getFloorType(), floorType.LowCarpet);
			assertEquals(testMap[5][0].getTileType(), tileType.Impassable);
			assertEquals(testMap[4][8].getFloorType(), floorType.Plain);
		}
		catch(Exception e)
		{
			
		}
	}
	
}
