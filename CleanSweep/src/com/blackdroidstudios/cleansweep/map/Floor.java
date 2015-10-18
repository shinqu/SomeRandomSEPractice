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
	private Image sprite;
	private Color color;
	private int x;
	private int y;
	private int dirt;
	private floorType myFloorType;
	private tileType myTileType;
	private boolean IsVisited;
	
	
	//Custom Constructor
	/**
	 * @author Armando Garcin
	 * @param _x The X coordinate in the map
	 * @param _y the Y coordinate in the map
	 * @param _floorType The type of floor. 
	 * @param color Color of the Floor
	 * @param dirt Number of dirt to be added
	 * 
	 * <p>Use "Tile.floorType" to search the 4 options: Plain, LowCarpet, HighCarper, and ChargingStation!</p>
	 * <p>E.G. Tile.floorType.Plain</p>
	 */
	public Floor(int x, int y, floorType floorType, Color color, int dirt)
	{
		//Auto generated, no need to reference from constructor
		this.x = x;
		this.y = y;
		this.myFloorType = floorType;
		this.myTileType = tileType.Passable;
		this.color = color;
		this.dirt = dirt;
		this.neighbours = new ArrayList<Tile>();
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
		return color;
	}
	
	public boolean getIsVisited()
	{
		return this.IsVisited;
	}

	@Override
	public ArrayList<Tile> getNeighbours() 
	{
		// TODO Auto-generated method stub
		return neighbours;
	}

	@Override
	public void addNeighbour(Tile neighbour)
	{
		// TODO Auto-generated method stub
		this.neighbours.add(neighbour);
	}

	@Override
	public Tile getEast() 
	{
		// TODO Auto-generated method stub
		for(Tile _floor : neighbours)
		{
			if(_floor.getX() > this.x)
			{
				return _floor;
			}
		}
		return null;
	}

	@Override
	public Tile getNorth() 
	{
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
		for(Tile _floor : neighbours)
		{
			if(_floor.getY() < this.y)
			{
				return _floor;
			}
		}
		return null;
	}

	@Override
	public Tile getWest() 
	{
		for(Tile _floor : neighbours)
		{
			if(_floor.getX() < this.x)
			{
				return _floor;
			}
		}
		return null;
	}
	
}
