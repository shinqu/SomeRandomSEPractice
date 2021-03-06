package com.blackdroidstudios.cleansweep.vacuum;

import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensor;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.reportlog.Reporter;

public class VacuumImpl implements Vacuum {
	private int dirtHolder;
	private boolean holderFull;  //EmptyMe Indicator ???
	private static DirtSensor dirtSensor;
	private Tile tile;
	private int tileDirt;
	
	/**
	 * Constructor for VacuumImpl.  Passes DirtSensor instance as parameter,
	 * Initializes dirtHolder to 0 and holder boolean to false.
	 */
	public VacuumImpl() 
	{
		dirtHolder = 0;
		holderFull = false;
		
	}
	
	
	/**
	 * Allows Vacuum to utilize dirt sensor capabilities
	 * @param ds sets dirtSensor ControlSystem class to this parameter
	 */
	public void setDirtSensor(DirtSensor ds) {
		dirtSensor = ds;
	}
	
	/**
	 * Gets amount in dirtHolder
	 * @return int dirtHolder
	 */
	public int getDirtHolder(){
		return dirtHolder;
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
			Reporter.getInstance().printGUI("CleanSweep needs to empty dirt holder");
		}
		return holderFull;
	}
	
	public int setTileDirt() {
		tileDirt = dirtSensor.detectDirt(tile);
		return tileDirt;
	}
	
	/**
	 * Subtracts one unit of dirt from tile and adds one unit of dirt to dirtHolder
	 */
	public void removeDirt(){
		if (tileDirt == 0) {
			Reporter.getInstance().printGUI("All dirt has been cleaned from tile");
		}
		else {
		tileDirt -= 1;
		dirtHolder += 1;
		Reporter.getInstance().printGUI("CleanSweep has removed a unit of dirt from tile");
		}
	}
	
	
	
	/**
	 * To be used when dirtHolder > 50.  Should be implemented through logic sequence
	 */
	public void emptyDirtHolder() 
	{  
			
			dirtHolder = 0; 
	}
	
	
	/**
	 * Method used to provide logic of VacuumImpl.  Will serve as main function for VacuumImpl.
	 * 
	 */
	public void cleanUp() 
	{ 
		holderCheck();
		getDirtHolder();
		
			while (tileDirt >= 0 && dirtHolder < 50)  {
			removeDirt();
		}	
		Reporter.getInstance().printGUI("There is no dirt to clean or dirt holder is full!");
		}
	

}
