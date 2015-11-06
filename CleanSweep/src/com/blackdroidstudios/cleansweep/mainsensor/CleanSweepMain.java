package com.blackdroidstudios.cleansweep.mainsensor;

import java.util.ArrayList;

import com.blackdroidstudios.cleansweep.battery.Battery;
import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensorFactory;
import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensorInter;
import com.blackdroidstudios.cleansweep.gui.GUIControl;
import com.blackdroidstudios.cleansweep.gui.GUIObserver;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.map.Tile.floorType;
import com.blackdroidstudios.cleansweep.movementsensor.MovementControl;
import com.blackdroidstudios.cleansweep.movementsensor.ReturnPathCarrier;
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
	private Battery battery;
	
	//Constructor
	public CleanSweepMain()
	{
		//Initialize the Vacuum Cleaner!!
		//Note: do not start it, just prepare everything that is needed
		movementSensor = new MovementControl();
		dirtSensor = DirtSensorFactory.createDirtSensor();
		//Start the Reporter Singleton
		Reporter.getInstance();
		battery = new Battery();
		guiControl = new GUIControl();
	}
	
	
	public void startVacuum(Tile _startTile) throws Exception
	{
		guiControl.initializeGUI();
		
		battery.setMyCurrentFloor(_startTile.getFloorType());
		movementSensor.setStartTile(_startTile);
		surfaceSensor = SurfaceSensorFactory.getNewSensor(_startTile);
		surfaceSensor.setChargingStation(_startTile);
		
		
		onUpdate();
	}
	
	public void onUpdate() throws InterruptedException
	{
		ReturnPathCarrier rtp = null;
		//Continue to operate, until we've visited ALL the floor
		while(!movementSensor.getOpenTiles().isEmpty())
		{
			
			boolean dirtlimit = dirtSensor.stopCleaning();
			rtp = movementSensor.chooseCS(movementSensor.getCurrentTile());
			long batterylimit = battery.getCharge() - rtp.getCost();
			if(dirtlimit == false && batterylimit > 5){
			
			//Step 2
			//If everything went well with the battery-n-dirt check up, let's move!
				movementSensor.Move();
				dirtSensor.cleanTile(movementSensor.getCurrentTile());
				surfaceSensor.registerMove(movementSensor.getCurrentTile());
				battery.setMyCurrentFloor(surfaceSensor.getCurrentFloorType());
				battery.move(surfaceSensor.getPrevious(), surfaceSensor.getCurrent());
				System.out.println("In regular move condition\n");
				guiControl.refreshGUI();
				printAll(rtp);
			
				Thread.sleep(500);
			}
			else if(dirtlimit == true || batterylimit <= 5){
				rtp = movementSensor.chooseCS(movementSensor.getCurrentTile());
				Tile temp = null;
				for(int i = 0; i < rtp.getPath().size(); i++){
					System.out.println("In return move condition \n");
					temp = rtp.getPath().get(i);
					movementSensor.setCurrentTile(temp);
					surfaceSensor.registerMove(movementSensor.getCurrentTile());
					battery.setMyCurrentFloor(surfaceSensor.getCurrentFloorType());
					battery.move(surfaceSensor.getPrevious(), surfaceSensor.getCurrent());
					guiControl.refreshGUI();
					printAll(rtp);
					
					Thread.sleep(500);
				}
				System.out.println("About to charge.");
				battery.chargeCS();
				//How to empty the dirt at the charging station?
			}
		}
	}
	
	/**
	 * Troubleshooting method, prints relevant data to console.
	 */
	private void printAll(ReturnPathCarrier rtp){
		System.out.println("MOVE DETAILS:");
		System.out.println("Cleansweep is at: (" + movementSensor.getCurrentTile().getX() + ", " + movementSensor.getCurrentTile().getY() + ")");
		System.out.println("Battery charge: " + battery.getCharge());
		System.out.println("Return cost: " + rtp.getCost() + ", Battery - Cost = " + (battery.getCharge() - rtp.getCost()));
		System.out.println();
	}
	
}
