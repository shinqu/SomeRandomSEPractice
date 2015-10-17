package com.blackdroidstudios.cleansweep.map;

/**
 * @author Armando Garcin
 */

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

public class Wall implements Tile 
{
	//Variables
	private int x;
	private int y;
	
	public Wall(int _x, int _y)
	{
		//Wall is impassable by default
		
	}

	@Override
	public tileType getTileType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public floorType getFloorType() {
		// You cannot pass a wall (unless you're a ghost), 
		return null;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDirt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Image getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tile> getNeighbours() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNeighbour(Tile _neighbour) {
		// TODO Auto-generated method stub
		
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
	public Tile getEast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile getWest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsVisited(boolean isVisited) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsVisited() {
		// TODO Auto-generated method stub
		return false;
	}

}
