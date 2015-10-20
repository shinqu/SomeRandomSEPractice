package com.blackdroidstudios.cleansweep.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.blackdroidstudios.cleansweep.map.FloorMap;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.movementsensor.MovementControl;

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
			assertEquals(mc.getCurrentTile().getY(), 1);
			assertEquals(mc.getCurrentTile().getNeighbours().size(), 3);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
