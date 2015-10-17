package com.blackdroidstudios.cleansweep.map;

/**
 * @author Armando Garcin
 * 
 */

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

public class Floor implements Tile
{

	//Variables
	private ArrayList<Tile> neighbours;
	private tileType myTileType;
	private floorType myFloorType;
	private Color color;
	private Image sprite;
	private int x;
	private int y;
	private int dirt;
	
	//Custom Constructor
	/**
	 * @author Armando Garcin
	 * @param _x The X coordinate in the map
	 * @param _y the Y coordinate in the map
	 * @param _floorType The type of floor. <p>Use "Tile.floorType" to search the 4 options: Plain, LowCarpet, HighCarper, and ChargingStation!</p>
	 * <p>E.G. Tile.floorType.Plain</p>
	 * 
	 * @Note 
	 * TileType will always be Passable, since this is a floor tile. 
	 */
	public Floor(int _x, int _y, floorType _floorType)
	{
		//Auto generated, no need to reference from constructor
		myTileType = tileType.Passable;
		x = _x;
		y = _y;
		myFloorType = _floorType;
	}
	
	@Override
	public tileType getTileType() 
	{
		return myTileType;
	}

	@Override
	public floorType getFloorType() 
	{
		return myFloorType;
	}

	@Override
	public int getX() 
	{
		return x;
	}

	@Override
	public int getY() 
	{
		return y;
	}

	@Override
	public int getDirt() 
	{
		// TODO Auto-generated method stub
		return dirt;
	}

	@Override
	public Image getSprite() 
	{
		// TODO Auto-generated method stub
		return sprite;
	}

	@Override
	public Color getColor() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tile> getNeighbours() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNeighbour(Tile _neighbour)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tile getNorth() 
	{
		// TODO Auto-generated method stub
		for(Tile _floor : neighbours)
		{
			if(_floor.getY() > this.y)
			{
				return _floor;
			}
		}
		return null;
	}

	@Override
	public Tile getSouth() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile getEast() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile getWest() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
