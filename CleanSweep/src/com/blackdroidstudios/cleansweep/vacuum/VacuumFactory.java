package com.blackdroidstudios.cleansweep.vacuum;

public class VacuumFactory {
	
	public static Vacuum createVacuum() 
	{
		return new VacuumImpl();
	}

}
