package com.blackdroidstudios.cleansweep.gui;

public class GUIControl 
{
	//Variables
	GUIFrame frame;
	GUIPanel panel;
	
	public GUIControl()
	{
		
	}
	
	//Setup the GUI
	public void initializeGUI()
	{
		frame = new GUIFrame();
		panel = new GUIPanel();
		
		panel.initializePanel();
		
		//Add the Panel to the Frame
		frame.addPanel(panel);
	}
	
	/**
	 * To be coded later...
	 */
	public void addMap()
	{
		
	}
	
}
