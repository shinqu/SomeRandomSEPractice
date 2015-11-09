package com.blackdroidstudios.cleansweep.mainsensor;

import com.blackdroidstudios.cleansweep.battery.Battery;
import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensorFactory;
import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensorInter;
import com.blackdroidstudios.cleansweep.gui.GUIControl;
import com.blackdroidstudios.cleansweep.gui.GUIObserver;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
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
	private static final int MINIMUM_BATTERY = 30;//You can change this to your hearts content, just don't go too low on the minimum
	//Controllers
	private MovementControl movementSensor;
	private GUIControl guiControl;
	private DirtSensorInter dirtSensor;
	private Battery battery;
	
	//Constructor
	public CleanSweepMain()
	{
		//Initialize the Vacuum Cleaner!!
		//Note: do not start it, just prepare everything that is needed
		movementSensor = new MovementControl();
		dirtSensor = DirtSensorFactory.createDirtSensor();
		battery = new Battery();
		//Start the Reporter Singleton
		Reporter.getInstance();
		
		guiControl = new GUIControl();
	}
	
	
	public void startVacuum(Tile _startTile) throws Exception
	{
		guiControl.initializeGUI();
		movementSensor.setStartTile(_startTile);
		
		onUpdate();
	}
	
	public void onUpdate() throws InterruptedException
	{
		//Continue to operate, until we've visited ALL the floor
		while(!movementSensor.getOpenTiles().isEmpty())
		{
			//In case we are out of battery, make an emergency break!
			if(battery.getBatteryLevel() <= 0)
			{
				//ErrorReport goes here!!!
				break;
			}
			
			//Step 1
			//Check battery and dirt levels
			if(battery.getBatteryLevel() <= MINIMUM_BATTERY || dirtSensor.isFull())
			{
				movementSensor.returnToCS();
			}
			
			
			//Step 2
			//If everything went well with the battery-n-dirt check up, let's move!
			battery.spendBattery(movementSensor.Move());
			
			if(!dirtSensor.cleanCheck(movementSensor.getCurrentTile()))
			{
				movementSensor.returnToCS();
				movementSensor.registerDirtyTile(movementSensor.getCurrentTile());
			}else
			{
				
			}
			
			//Step 3: check if we are in a charging Station!
			if(movementSensor.getCurrentTile().getFloorType() == floorType.ChargingStation)
			{
				battery.chargeCS();
				dirtSensor.dumpDirt();
			}
			
			guiControl.refreshGUI();
			
			Thread.sleep(250);
		}
		
		while(movementSensor.getCurrentTile().getFloorType() != floorType.ChargingStation)
		{
			movementSensor.returnToCS();
			movementSensor.Move();
			if(movementSensor.getCurrentTile().getFloorType() == floorType.ChargingStation)
			{
				battery.chargeCS();
				dirtSensor.dumpDirt();
			}
			guiControl.refreshGUI();
			Thread.sleep(250);
		}
	}
	
}
