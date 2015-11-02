package com.blackdroidstudios.cleansweep.mainsensor;

import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensorFactory;
import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensorInter;
import com.blackdroidstudios.cleansweep.gui.GUIControl;
import com.blackdroidstudios.cleansweep.gui.GUIObserver;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.movementsensor.MovementControl;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;
import com.blackdroidstudios.cleansweep.surfacesensor.SurfaceSensor;
import com.blackdroidstudios.cleansweep.surfacesensor.SurfaceSensorFactory;

/**
 * This is where the Vacuum Cleaner will be controlled.
 * <p>The Surface, Dirt, and Movement Sensor will come together and work in harmony here (I hope ._.)</p>
 * @author Armando Garcin
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
		//Start the Reporter Singleton
		Reporter.getInstance();
		
		guiControl = new GUIControl();
	}
	
	
	public void startVacuum(Tile _startTile) throws Exception
	{
		guiControl.initializeGUI();
		
		movementSensor.setStartTile(_startTile);
		surfaceSensor = SurfaceSensorFactory.getNewSensor(_startTile);
		
		
		
		onUpdate();
	}
	
	public void onUpdate() throws InterruptedException
	{
		int tempCount = 0;
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
			//Check battery and dirt levels
			if(tempCount > 60)
			{
				break;
			}
			
			//Step 2
			//If everything went well with the battery-n-dirt check up, let's move!
			movementSensor.Move();
			
			guiControl.refreshGUI();
			
			tempCount++;
			Thread.sleep(500);
		}
	}
	
}
