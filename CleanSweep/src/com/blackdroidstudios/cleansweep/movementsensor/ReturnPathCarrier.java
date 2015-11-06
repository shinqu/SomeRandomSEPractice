package com.blackdroidstudios.cleansweep.movementsensor;

import java.util.ArrayList;
import com.blackdroidstudios.cleansweep.map.Tile;

/**
 * Simple class that allows keeping a return path to the charging station and that path's cost, so it is not necessary
 * to iterate through a list that has already been iterated through. Serves the purpose of increasing efficiency.
 * @author StevenKiley
 *
 */
public class ReturnPathCarrier {
	private ArrayList<Tile> path;
	private long travelCost;
	
	ReturnPathCarrier(){
		travelCost = 0;
		path = new ArrayList<Tile>();
	}
	
	public void addTile(Tile t){
		path.add(t);
	}
	
	public ArrayList<Tile> getPath(){
		return this.path;
	}
	
	public void setCost(long c){
		travelCost += c;
	}
	
	public long getCost(){
		return this.travelCost;
	}
}
