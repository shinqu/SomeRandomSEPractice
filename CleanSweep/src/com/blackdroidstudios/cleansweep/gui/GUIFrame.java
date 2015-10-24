package com.blackdroidstudios.cleansweep.gui;

/*
 * 
 * @Author Armando Garcin
 * 10/10/2015
 * 
 * :)
 * 
 */


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIFrame 
{
	//Static Variables
	public static final int FRAME_SIZE_X = 1600;
	public static final int FRAME_SIZE_Y = 1200;
	public static final int GUIPANEL_SIZE_Y = 900;
	public static final int CONSOLELOG_SIZE_Y = 300;
	
	//Variables
	private JFrame frame;
	
	public GUIFrame()
	{
		//Create new Frame
		frame = new JFrame("Clean Sweep SE459");
		//Set Size
		frame.setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
		//Setting new listener for when Close button is clicked
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}
		});
		
		frame.setResizable(false);
		//Show the Frame
		frame.setVisible(true);
	}
	
	public void addPanel(JPanel _panel)
	{
		frame.add(_panel);
	}
	
	
}
