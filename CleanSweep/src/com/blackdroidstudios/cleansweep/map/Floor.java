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
	private int x;
	private int y;
	private floorType myFloorType;
	private tileType myTileType;
	private Color color;
	private Image sprite;
	private int dirt;
	private boolean IsVisited;
	private ArrayList<Tile> neighbours;
	
	//Custom Constructor
	/**
	 * @author Armando Garcin
	 * @param _x The X coordinate in the map
	 * @param _y the Y coordinate in the map
	 * @param _floorType The type of floor. <p>Use "Tile.floorType" to search the 4 options: Plain, LowCarpet, HighCarper, and ChargingStation!</p>
	 * <p>E.G. Tile.floorType.Plain</p>
	 * 
	 */
	public Floor(int x, int y, floorType floorType)
	{
		//Auto generated, no need to reference from constructor
		this.x = x;
		this.y = y;
		this.myFloorType = floorType;
	}
	
	public Floor(int x, int y, floorType floorType, tileType tileType, Color color, Image sprite, int dirt)
	{
		//Auto generated, no need to reference from constructor
		this.x = x;
		this.y = y;
		this.myFloorType = floorType;
		this.myTileType = tileType;
		this.color = color;
		this.sprite = sprite;
		this.dirt = dirt;
		this.neighbours = new ArrayList<>();
	}
	
	@Override
	public tileType getTileType() 
	{
		return myTileType;
	}

	@Override
	public floorType getFloorType() 
	{
		return null;
	}

	@Override
	public int getX() 
	{
		return x;
	}
	
	@Override
	public void setX(int x) 
	{
		this.x = x;
	}

	@Override
	public int getY() 
	{
		return y;
	}
	
	@Override
	public void setY(int y) 
	{
		this.y = y;
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
	public void setIsVisited(boolean isVisited)
	{
		this.IsVisited = isVisited;
	}

	@Override
	public Tile getEast() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		for(Tile _floor : neighbours)
		{
			if(_floor.getY() > this.y)
			{
				return _floor;
			}
		}
=======
>>>>>>> 142bc39e0c94e50f544f4017ba0f5ef358220c82
		return null;
	}

	@Override
	public Tile getNorth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile getSouth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile getWest() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
