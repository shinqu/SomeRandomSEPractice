package com.blackdroidstudios.cleansweep.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JPanel;

import com.blackdroidstudios.cleansweep.map.FloorMap;

public class GUIPanel extends JPanel
{
	//Variables
	private Rectangle[][] floorMap;
	
	//private ArrayList<Tiles> tilesToPaint;
	
	public GUIPanel()
	{
		floorMap = new Rectangle[FloorMap.FLOOR_SIZE_X][FloorMap.FLOOR_SIZE_Y];
	}
	
	public void initializePanel()
	{
		validate();
		repaint();
	}
	
	public void paintMap(Graphics2D _g2d)
	{
		for(int x = 0; x < FloorMap.FLOOR_SIZE_X; x++)
		{
			for(int y = 0; y < FloorMap.FLOOR_SIZE_Y; y++)
			{
				if((x + y) % 2 == 0)
				{
					_g2d.setColor(Color.BLACK);
					_g2d.fillRect(x*125, y*125, 125, 125);
				}else
				{
					_g2d.setColor(Color.WHITE);
					_g2d.fillRect(x*125, y*125, 125, 125);
				}
			}
		}
	}
	
	public void addActor()
	{
		//Do stuff
		//tilesToPaint.add(tile);
		
		validate();
		repaint();
	}
	
	//REALLY IMPORTANT!!!!! O.o
	//Paint function
	public void paint(Graphics gd)
	{
		Graphics2D g2d = (Graphics2D)gd;
		//Paint map
		paintMap(g2d);
	}
}
