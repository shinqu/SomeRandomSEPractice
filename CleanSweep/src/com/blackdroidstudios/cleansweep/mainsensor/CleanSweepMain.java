package com.blackdroidstudios.cleansweep.mainsensor;

import com.blackdroidstudios.cleansweep.movementsensor.MovementControl;

/**
 * This is where the Vacuum Cleaner will be controlled.
 * <p>The Surface, Dirt, and Movement Sensor will come together and work in harmony here (I hope ._.)</p>
 * @author Armando
 *
 */

public class CleanSweepMain 
{
	//Variables
	private MovementControl movementSensor;
	
	//Constructor
	public CleanSweepMain()
	{
		movementSensor = new MovementControl();
	}
	
	
}
