package com.blackdroidstudios.cleansweep.map;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.map.Tile.tileType;

public class ChargingStation implements Tile{
	private ArrayList<Tile> neighbours;
	private Image sprite;
	private Color color;
	private int x;
	private int y;
	private floorType myFloorType;
	private tileType myTileType;
	
	public ChargingStation(int x, int y, floorType floorType, Color color)
	{
		//Auto generated, no need to reference from constructor
		this.x = x;
		this.y = y;
		this.myFloorType = floorType;
		this.myTileType = tileType.Passable;
		this.color = color;
		this.neighbours = new ArrayList<Tile>();
	}

	public tileType getTileType() {return tileType.Passable;}

	public floorType getFloorType() {return floorType.ChargingStation;}

	public int getX() {return this.x;}

	public int getY() {return this.y;}

	public int getDirt() {return 0;}

	public Image getSprite() {return this.sprite;}

	public Color getColor() {return this.color;}

	public ArrayList<Tile> getNeighbours() {return this.neighbours;}

	public void addNeighbour(Tile _neighbour) {
		this.neighbours.add(_neighbour);
	}

	public Tile getEast() {
		for(Tile _floor : neighbours)
		{
			if(_floor.getX() > this.x)
			{
				return _floor;
			}
		}
		return null;
	}

	public Tile getNorth() {
		for(Tile _floor : neighbours)
		{
			if(_floor.getY() > this.y)
			{
				return _floor;
			}
		}
		return null;
	}

	public Tile getSouth() {
		for(Tile _floor : neighbours)
		{
			if(_floor.getY() < this.y)
			{
				return _floor;
			}
		}
		return null;
	}

	public Tile getWest() {
		for(Tile _floor : neighbours)
		{
			if(_floor.getX() < this.x)
			{
				return _floor;
			}
		}
		return null;
	}
	
	public long chargeVacuum(long _currentCharge){
		if(_currentCharge <= 95){
			return (_currentCharge + 5);
		}
		else{
			long diff = 100 - _currentCharge;
			return (_currentCharge + diff);
		}
	}

}
