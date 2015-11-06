package com.blackdroidstudios.cleansweep.dirtsensor;

/**
 * Factory class for DirtSensor.  Creates a new instance of a DirtSensor.  Not sure what the best argument is to run through it.  
 * @author brooney
 *
 */
public class DirtSensorFactory 
{
	
	public static DirtSensorInter createDirtSensor()
	{
		return new DirtSensor();
	}

}
