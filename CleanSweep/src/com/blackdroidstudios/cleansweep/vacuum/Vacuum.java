package com.blackdroidstudios.cleansweep.vacuum;

import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensor;

public interface Vacuum {
	
	public void setDirtSensor(DirtSensor ds);
	
	public int getDirtHolder();
	
	public boolean holderCheck ();
	
	public int setTileDirt();
	
	public void removeDirt();
	
	public void emptyDirtHolder();
	
	public void cleanUp();
	
}
