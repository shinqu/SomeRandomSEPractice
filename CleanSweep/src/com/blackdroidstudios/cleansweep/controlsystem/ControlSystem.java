package com.blackdroidstudios.cleansweep.controlsystem;

import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensor;

/**
 * ControlSystem Interface used as blueprint for all ControlSystem types.  
 * @author brooney
 *
 */
public interface ControlSystem 
{
	
	public void setDirtSensor(DirtSensor ds);
	
	public int getDirtHolder();
	
	public boolean holderCheck ();
	
	public int setTileDirt();
	
	public void removeDirt();
	
	public void emptyDirtHolder();
	
	public void cleanUp();
	

}
