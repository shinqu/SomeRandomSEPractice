package com.blackdroidstudios.cleansweep.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.blackdroidstudios.cleansweep.map.FloorMap;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.movementsensor.MovementControl;
import com.blackdroidstudios.cleansweep.movementsensor.MovementMap;

public class MovementSensorTests 
{
	@Test
	public void MSOverallCheckup()
	{
		try
		{
			FloorMap map = new FloorMap();
			Tile[][] testMap = map.getMap();
			MovementControl mc = new MovementControl();
			mc.setStartTile(testMap[0][0]);
			assertEquals(mc.getCurrentTile(), testMap[0][0]);
			assertEquals(mc.getCurrentTile().getNeighbours().size(), 2);
			assertEquals(mc.getCurrentTile().getSouth(), null);
		}catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void MSSimpleStepTest()
	{
		try
		{
			FloorMap map = new FloorMap();
			Tile[][] testMap = map.getMap();
			MovementControl mc = new MovementControl();
			mc.setStartTile(testMap[0][0]);
			assertEquals(mc.getCurrentTile(), testMap[0][0]);
			assertEquals(mc.getPath().isEmpty(), true);
			mc.Move();
			assertEquals(mc.getPath().isEmpty(), false);
			mc.Move();
			assertEquals(mc.getCurrentTile().getX(), 0);
			assertEquals(mc.getCurrentTile().getY(), 0);
			mc.Move();
			assertEquals(mc.getCurrentTile().getX(), 0);
			assertEquals(mc.getCurrentTile().getY(), 1);
			assertEquals(mc.getCurrentTile().getNeighbours().size(), 3);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test 
	public void MSVisitedTilesTest()
	{
		try
		{
			FloorMap map = new FloorMap();
			Tile[][] testMap = map.getMap();
			MovementControl mc = new MovementControl();
			mc.setStartTile(testMap[0][0]);
			assertEquals(1, mc.getVisitedTiles().size());
			mc.Move();
			assertEquals(mc.getPath().isEmpty(), false);
			mc.Move();
			assertEquals(1, mc.getVisitedTiles().size());
			mc.Move();
			assertEquals(2, mc.getVisitedTiles().size());
		}catch(Exception e)
		{
			
		}
	}
	
	@Test 
	public void MSLongPathTest()
	{
		try 
		{
			FloorMap map = new FloorMap();
			Tile[][] testMap = map.getMap();
			MovementMap mp = new MovementMap();
			ArrayList<Tile> testPath = mp.findPath(testMap[0][0], testMap[4][0]);
			assertEquals(5, testPath.size());
			assertEquals(0, testPath.get(0).getX());
			assertEquals(4, testPath.get(4).getX());
		}catch(Exception e)
		{
			
		}
	}
	
	
}
