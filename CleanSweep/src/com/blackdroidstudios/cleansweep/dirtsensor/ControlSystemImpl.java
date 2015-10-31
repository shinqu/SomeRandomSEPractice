package com.blackdroidstudios.cleansweep.dirtsensor;

import com.blackdroidstudios.cleansweep.movementsensor.MovementMap;
import com.blackdroidstudios.cleansweep.movementsensor.MovementControl;
import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensor;


/**
// * ControlSystemImpl class, used to implement ControlSystem Interface. ControlSystem types are to be initiated
 * using the ControlSystemFactory. 
 * @author brooney
 *
 */
public class ControlSystemImpl implements ControlSystem{
	private int dirtHolder;
	private boolean holderFull;  //EmptyMe Indicator ???
	
	/**
	 * Constructor for ControlSystemImpl.  Passes DirtSensor instance as parameter,
	 * Initializes dirtHolder to 0 and holder boolean to false.
	 */
	public ControlSystemImpl(DirtSensor ds) {
		this.ds = ds;
		dirtHolder = 0;
		holderFull = false;
		
	}
	
	/**
	 * Checks to see if dirtHolder if full or not. True if full, false if < 50 units
	 * 
	 * @return boolean holderFull 
	 */
	public boolean holderCheck (){
		if (dirtHolder < 50) {
			holderFull = false;
		}
		else {
			holderFull = true;
			System.out.println("CleanSweep needs to empty dirt holder")
			emptyHolder();
		}
	}
	
	/**
	 * Subtracts one unit of dirt from tile and adds one unit of dirt to dirtHolder
	 */
	public void removeDirt(){
		ds.dirtDetected -= 1;
		dirtHolder += 1;
		System.out.println("CleanSweep has removed a unit of dirt from tile");
	}
	
	/**
	 * To be used when dirtHolder > 50.  Should be implemented through logic sequence
	 */
	public void emptyDirtHolder() {
			MovementMap.returnToCS();  
			dirtHolder = 0; 
	}
	
	
	/**
	 * Method used to provide logic of ControlSystemImpl.  Will serve as main functionfor ControlSystemImpl.
	 * 
	 */
	public void cleanUp() { 
		ds.cleanCheck(MovementControl.getCurrentTile());
		holderCheck();
			do {
				removeDirt();
			}
			while (!ds.cleanState  && !holderFull);
		
			if (!ds.cleanState && holderFull) {
				MovementMap.registerOpenTiles(MovementControl.getCurrentTile());
				emptyDirtHolder();
			}
			
			if else (ds.cleanState && !holderFull) {
				MovementControl.move();
				ds.cleanCheck(MovementControl.getCurrentTile());
				holderCheck();
			}
			else {
				emptyDirtHolder();
			}
	}
	
	

}
