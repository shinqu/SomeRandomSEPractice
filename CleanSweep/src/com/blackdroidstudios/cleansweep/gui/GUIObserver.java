package com.blackdroidstudios.cleansweep.gui;

import com.blackdroidstudios.cleansweep.map.Tile;

/**
 * This is a Singleton class to be used by anyone and report to the panel 
 * @author Armando Garcin
 *
 */
public class GUIObserver 
{
	private static GUIPanel panel;
	
	public static void registerPanel(GUIPanel _panel)
	{
		panel = _panel;
	}
	
	public static void addNewSeenTile(Tile _tile)
	{
		if(panel != null)
		{
			panel.addNewTile(_tile);
		}
	}
	/**
	 * This method will tell 
	 * @param _x
	 * @param _y
	 */
	public static void updateCleanSweepPos(int _x, int _y)
	{
		
	}
	
	
}
