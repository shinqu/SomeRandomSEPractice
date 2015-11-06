package com.blackdroidstudios.cleansweep.dirtsensor;

import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensor;

/**
 * ControlSystemFactory class used to create instance of ControlSystem object. Parameter of DirtSensor objects helps to tie 
 * one instance of each to one another.
 * @author brooney
 *
 */
public class ControlSystemFactory {
	

		
		public static ControlSystem createControlSystem(DirstSensor ds) //Not sure if correct way to bring together both classes
		{
			ControlSystem cs = new ControlSystemImpl(ds);
			return cs;
		}

	}

