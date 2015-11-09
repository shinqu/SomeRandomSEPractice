package com.blackdroidstudios.cleansweep.gui;

/**
 * This Class is responsible for displaying the Log Console
 * @author Armando Garcin
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.blackdroidstudios.cleansweep.reportlog.Reporter;

public class GUIConsole extends JPanel
{
	//Static Variables
	private static final int FONT_TITLE_SIZE = 32;
	private static final String TITLE_STRING = "Clean Sweep App (^_^)";
	
	//Variables
	private String titleTV;
	private String posXTV;
	private String posYTV;
	private String currentDirt;
	private String currentBattery;
	
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
		
		g2d.clearRect(0, 0, GUIFrame.CONSOLELOG_SIZE_X, GUIFrame.FRAME_SIZE_Y);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GUIFrame.CONSOLELOG_SIZE_X, GUIFrame.FRAME_SIZE_Y);
		
		paintHeader(g2d);
		
		
	}
	
	public void paintHeader(Graphics _g2d)
	{
		_g2d.setColor(Color.GREEN);
		_g2d.setFont(Font.getFont("Arial"));
		_g2d.drawString(TITLE_STRING, 15, 15);
		_g2d.drawString("Current Position: X:" + Reporter.getInstance().getCurrentLocX() + " Y:" + Reporter.getInstance().getCurrentLocY(), 15, 30);
		_g2d.drawString("Battery: " + Reporter.getInstance().getCurrentBattery(), 15, 45);
		_g2d.drawString("Dirt Storage: " + Reporter.getInstance().getCurrentDirtLvl(), 15, 70);
	}
	
	
}
