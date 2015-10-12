package com.blackdroidstudios.cleansweep.map;

/**
 * @author Armando Garcin
 */

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

public interface Tile 
{
	//Enums
	public enum tileType{Passable, Impassable};
	public enum floorType{Plain, LowCarpet, HighCarpet, ChargingStation};
	
	/**
	 * @author Armando Garcin
	 * <p>Get the type of Tile, Passable or Impassable.</p>
	 */
	//Get the type of tile
	public tileType getTileType();
	/**
	 * @author Armando Garcin
	 * <p>Get the type of Floor: Plain, LowCarpet, HighCarpet, and ChargingStation.</p>
	 */
	//Get the type of floor
	public floorType getFloorType();
	/**
	 * @return The X coordinate in the Map
	 */
	//Get X Coord
	public int getX();
	/**
	 * @return The Y coordinate in the Map
	 */
	//Get Y Coord
	public int getY();
	/**
	 * @return The number of dirt units in the current tile
	 */
	//Get Dirt Units
	public int getDirt();
	/**
	 * @return The Sprite of the Tile, if Available
	 */
	//Get Sprite (for the GUI only)
	public Image getSprite();
	/**
	 * @return The color of the Tile
	 */
	//Get The color (for the GUI only)
	public Color getColor();
	/**
	 * @return The Neighbour Tiles
	 */
	public Tile[] getNeighbours();
	/**
	 * @param _neighbour Add a new Neighbour to the Tile
	 *<p> Cannot hold more than 4 Neighbours, and if the are not adjacent to the Tile</p>
	 */
	public void addNeighbour(Tile _neighbour);
}
