package com.blackdroidstudios.cleansweep.gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GUIControl 
{
	//Variables
	private GUIFrame frame;
	private JPanel panelContainer;
	private GUIPanel guiPanel;
	private GUIConsole consolePanel;
	
	public GUIControl()
	{
		
	}
	
	//Setup the GUI
	public void initializeGUI()
	{
		frame = new GUIFrame();
		panelContainer = new JPanel();
		guiPanel = new GUIPanel();
		consolePanel = new GUIConsole();
		GUIObserver.registerPanel(guiPanel);
		/*
		 * Put the ErrorReporter Here!!
		 */
		
		guiPanel.setPreferredSize(new Dimension(GUIFrame.GUIPANEL_SIZE_X, GUIFrame.FRAME_SIZE_Y));
		consolePanel.setPreferredSize(new Dimension(GUIFrame.CONSOLELOG_SIZE_X, GUIFrame.FRAME_SIZE_Y));
		
		//Set the layout of the container
		panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.X_AXIS));
		
		panelContainer.add(guiPanel);
		panelContainer.add(consolePanel);
		
		//Add the Panel to the Frame
		frame.addPanel(panelContainer);
	}
	
	/**
	 * To be coded later...
	 */
	public void addMap()
	{
		
	}
	
	public int getFrameSizeX()
	{
		return frame.FRAME_SIZE_X;
	}
	
	public int getFrameSizeY()
	{
		return frame.FRAME_SIZE_Y;
	}
	
	public void refreshGUI()
	{
		guiPanel.refreshScreen();
		consolePanel.refreshScreen();
	}
	
}
