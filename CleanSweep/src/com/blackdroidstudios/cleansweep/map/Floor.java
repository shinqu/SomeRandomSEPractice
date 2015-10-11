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
	 */
	public Floor(int _x, int _y, floorType _floorType)
	{
		//Auto generated, no need to reference from constructor
		myTileType = tileType.Passable;
		
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
		return null;
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
	
}
