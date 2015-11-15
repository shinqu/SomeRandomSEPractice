package com.blackdroidstudios.cleansweep.controlsystem;


import com.blackdroidstudios.cleansweep.reportlog.Reporter;
import com.blackdroidstudios.cleansweep.battery.Battery;
import com.blackdroidstudios.cleansweep.dirtsensor.DirtSensor;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.surfacesensor.*;
import com.blackdroidstudios.cleansweep.vacuum.*;
import com.blackdroidstudios.cleansweep.movementsensor.*;


/**
// * ControlSystemImpl class, used to implement ControlSystem Interface. ControlSystem types are to be initiated
 * using the ControlSystemFactory. 
 * @author brooney
 *
 */
public class ControlSystemImpl implements ControlSystem {  //broadcast to a gui listener
	
	boolean dirtPresent;
	boolean isChargingStation;
	boolean cycleComplete;
	Tile lastChargingStation; 
	Tile nearestChargingStation;
	Tile lastTileVisited;
	int tileMoves;
	SurfaceSensor sensor;
	DirtSensor dirtSensor;
	VacuumImpl vacuum;
	MovementControl moveControl;
	MovementMap map;
	int totalCycles;
	
	public enum CycleState {
		Starting, Active, Paused, Complete
	}
	
	CycleState state;
	
	public void cycleChanger(){
		switch (state) {
		case Starting: 
			Reporter.getInstance().printGUI("Clean Sweep cleaning cycle is Starting"); //load instances needed of dirt sensor etc...
		case Active:
			Reporter.getInstance().printGUI("Clean Sweep cleaning cycle is Active"); //move, suck up dirt
			break;
		case Paused:
			Reporter.getInstance().printGUI("Clean Sweep cleaning cycle is Paused"); //find nearest cs, return to charging station, return to last tile
			break;
			
		case Complete: 
			Reporter.getInstance().printGUI("Clean Sweep cleaning cycle is Complete"); //return to original charging station, wait for new instructions
			break;
		}
	}
	
	public ControlSystemImpl(){
		lastChargingStation = map.getVisitedTiles().get(0);
		nearestChargingStation = map.getVisitedTiles().get(0);
	}
	
	public boolean maintainCleanCycle() {
		
		if (map.getOpenTiles().size() <= 0 && vacuum.returnToChargerCheck() == false && Battery.getCharge() > 0) {
			cycleComplete = true;
			totalCycles ++;
		}
		else {
			cycleComplete = false;
		}
		
		return cycleComplete;
		
	}
	
	public void allLocVisited() { // bring over from DirtSensor
		
	}
	
	public boolean dirtRemaining(Tile tile) {
		dirtPresent = false;
		return dirtPresent;
	}
	
	public Tile locateChargeStation(Tile tile) { // look 2 cells in every direction, bool isCS, go to that, if none found go to last CS
		return tile;
	}
	
	public void run(){
		Reporter.getInstance().printGUI("CleanSweep is now running!");
	}
}
