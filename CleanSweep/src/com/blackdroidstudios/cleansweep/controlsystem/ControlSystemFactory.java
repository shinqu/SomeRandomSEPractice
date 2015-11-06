package com.blackdroidstudios.cleansweep.controlsystem;

/**
 * ControlSystemFactory class used to create instance of ControlSystem object. Parameter of DirtSensor objects helps to tie 
 * one instance of each to one another.
 * @author brooney
 *
 */
public class ControlSystemFactory 
{
		
		public static ControlSystem createControlSystem() //Not sure if correct way to bring together both classes
		{
			return new ControlSystemImpl();
		}

}

