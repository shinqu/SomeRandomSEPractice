package com.blackdroidstudios.cleansweep.map;

/**
 * @author Armando Garcin
 */

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

public class Obstacle implements Tile 
{
	//Variables
	private ArrayList<Tile> neighbours;
	private Image sprite;
	private Color color;
	private int x;
	private int y;
	private tileType myTileType;

	//Argument Constructor
	public Obstacle(int _x, int _y)
	{
		this.neighbours = new ArrayList<Tile>();
		this.sprite = null;
		this.color = Color.RED;
		this.x = _x;
		this.y = _y;
		this.myTileType = tileType.Impassable;
	}
	public Obstacle(int _x, int _y, Image _sprite)
	{
		this.neighbours = new ArrayList<Tile>();
		this.sprite = _sprite;
		this.color = Color.RED;
		this.x = _x;
		this.y = _y;
		this.myTileType = tileType.Impassable;
	}

	@Override
	public tileType getTileType() 
	{
		return myTileType;
	}

	@Override
	public floorType getFloorType() 
	{
		// You cannot pass an Obstacle (unless you're a ghost).
		return null;
	}

	@Override
	public int getX() 
	{
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY()
	{
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public int getDirt() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Image getSprite() 
	{
		// TODO Auto-generated method stub
		return this.sprite;
	}

	@Override
	public Color getColor() 
	{
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public ArrayList<Tile> getNeighbours() 
	{
		// TODO Auto-generated method stub
		return this.neighbours;
	}

	@Override
	public void addNeighbour(Tile _neighbour) 
	{
		// TODO Auto-generated method stub
		this.neighbours.add(_neighbour);
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
