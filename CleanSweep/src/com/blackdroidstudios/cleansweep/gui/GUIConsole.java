package com.blackdroidstudios.cleansweep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GUIConsole extends JPanel
{
	
	public GUIConsole()
	{
		refreshScreen();
	}
	
	public void refreshScreen()
	{
		validate();
		repaint();
	}
	
	public void paint(Graphics gd)
	{
		Graphics2D g2d = (Graphics2D)gd;
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GUIFrame.FRAME_SIZE_X, GUIFrame.CONSOLELOG_SIZE_Y);
	}
	
}
