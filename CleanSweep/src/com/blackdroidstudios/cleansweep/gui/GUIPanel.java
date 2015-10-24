package com.blackdroidstudios.cleansweep.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.blackdroidstudios.cleansweep.map.FloorGenerator;
import com.blackdroidstudios.cleansweep.map.FloorMap;
import com.blackdroidstudios.cleansweep.map.Tile;

public class GUIPanel extends JPanel 
{
	
	//Static variables
	private static final int TILE_SIZE = 75; //This will be the size of the Tile
	
	//Variables
	private Timer timer;
	private Rectangle[][] floorMap;
	private ArrayList<Tile> visitedTiles;

	public GUIPanel() 
	{
		floorMap = new Rectangle[FloorMap.FLOOR_SIZE_X][FloorMap.FLOOR_SIZE_Y];
		visitedTiles = new ArrayList<Tile>();
	}

	/**
	 *<p> Refreshes the panel.</p>
	 *<p>If you just updated something in the panel, it's a good idea to use this! :)</p>
	 */
	public void refreshScreen() 
	{
		validate();
		repaint();
	}

	/*Action paintTimer = new AbstractAction() { // functionality of our timer:
		public void actionPerformed(ActionEvent e) {
			// if(pi < FloorMap.FLOOR_SIZE_X && pj < FloorMap.FLOOR_SIZE_Y){
			if (CheckIfAllTilesVisited() == false) 
			{
				//Move();
				repaint();
			} else
			{
				timer.stop();
			}
		}
	};*/


	// REALLY IMPORTANT!!!!! O.o
	// Paint function
	public void paint(Graphics gd) 
	{
		Graphics2D g2d = (Graphics2D) gd;
		
		//Clear all the screen before painting anything!
		g2d.clearRect(0, 0, GUIFrame.FRAME_SIZE_X, GUIFrame.GUIPANEL_SIZE_Y);
		
		// Paint map
		try 
		{
			paintMap(g2d);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintMap(Graphics2D _g2d) throws IOException 
	{
		for (int x = 0; x < FloorMap.FLOOR_SIZE_X; x++) 
		{
			for (int y = 0; y < FloorMap.FLOOR_SIZE_Y; y++) 
			{
				if ((x + y) % 2 == 0) 
				{
					_g2d.setColor(Color.BLACK);
					_g2d.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				} else 
				{
					_g2d.setColor(Color.WHITE);
					_g2d.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
			}
		}
	}
}
