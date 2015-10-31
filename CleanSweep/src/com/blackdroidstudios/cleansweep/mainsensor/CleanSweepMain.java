package com.blackdroidstudios.cleansweep.mainsensor;

import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensorFactory;
import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensorInter;
import com.blackdroidstudios.cleansweep.gui.GUIControl;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.movementsensor.MovementControl;
import com.blackdroidstudios.cleansweep.surfacesensor.SurfaceSensor;
import com.blackdroidstudios.cleansweep.surfacesensor.SurfaceSensorFactory;

/**
 * This is where the Vacuum Cleaner will be controlled.
 * <p>The Surface, Dirt, and Movement Sensor will come together and work in harmony here (I hope ._.)</p>
 * @author Armando
 *
 */

public class CleanSweepMain 
{
	//Variables
	//Controllers
	private MovementControl movementSensor;
	private GUIControl guiControl;
	private DirtSensorInter dirtSensor;
	private SurfaceSensor surfaceSensor;
	
	//Constructor
	public CleanSweepMain()
	{
		//Initialize the Vacuum Cleaner!!
		//Note: do not start it, just prepare everything that is needed
		movementSensor = new MovementControl();
		dirtSensor = DirtSensorFactory.createDirtSensor();
		
		guiControl = new GUIControl();
	}
	
	
	public void startVacuum(Tile _startTile) throws Exception
	{
		movementSensor.setStartTile(_startTile);
		surfaceSensor = SurfaceSensorFactory.getNewSensor(_startTile);
		
		onUpdate();
	}
	
	public void onUpdate() throws InterruptedException
	{
		//Continue to operate, until we've visited ALL the floor
		while(!movementSensor.getOpenTiles().isEmpty())
		{
			//In case we are out of battery, make an emergency break!
			/*
			 * 
			 * 
			 * Code goes here
			 * 
			 * 
			 */
			
			//Step 1
			
			
			Thread.sleep(1000);
		}
	}
	
}
