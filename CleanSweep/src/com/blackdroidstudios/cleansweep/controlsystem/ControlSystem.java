package com.blackdroidstudios.cleansweep.controlsystem;


import com.blackdroidstudios.cleansweep.map.*;

/**
 * ControlSystem Interface used as blueprint for all ControlSystem types.  
 * @author brooney
 *
 */
public interface ControlSystem extends Runnable
{
	
	public boolean maintainCleanCycle();
	
	public void allLocVisited(); // bring over from DirtSensor
	
	public boolean dirtRemaining(Tile tile);
	
	public Tile locateChargeStation(Tile tile); // look + 2 tiles in each direction 
	

}
